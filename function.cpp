#include "header.hpp"

void displayHelp()
{
    std::cout << "\ndu (c) 2018-21, Garth Santor\n";
    std::cout << "===========================================================\n";
    std::cout << "Version 2.0.0\n\n";
    std::cout << "A disk usage utility inspired by the UNIX du command.\n\n";
    std::cout << "Usage: du [-skhb] [--help] [--version] [--block-size=dddd] [folder]*\n\n";
    std::cout << "Examples:\n";
    std::cout << "  du\n";
    std::cout << "    > display the sum of the cluster sizes of each directory\n      starting with the cwd\n\n";
    std::cout << "  du folder\n";
    std::cout << "    > display the sum of the cluster sizes of each directory\n      starting with 'folder'\n\n";
    std::cout << "  du -h\n";
    std::cout << "    > display the results in a human-readable form\n\n";
    std::cout << "  du -s\n";
    std::cout << "    > display only the final summary\n\n";
    std::cout << "  du -b\n";
    std::cout << "    > display in bytes\n\n";
    std::cout << "  du -k\n";
    std::cout << "    > cluster size is 1024\n\n";
    std::cout << "  du -z\n";
    std::cout << "    > display the list sorted by size\n\n";
    std::cout << "  du -n\n";
    std::cout << "    > display the list sorted by name\n\n";
    std::cout << "  du -r\n";
    std::cout << "    > display the list in reverse order\n\n";
    std::cout << "  du --block-size=dddd\n";
    std::cout << "    > set the cluster size to the specified integer > 0\n\n";
    std::cout << "  du --help\n";
    std::cout << "    > displays the help\n\n";
    std::cout << "  du --version\n";
    std::cout << "    > displays version number in the format d.d.d\n";
}

void displayVersion()
{
    std::cout << "1.0.0\n";
}

std::string humanReadableSize(size_t size, int &blockSize)
{
    if (size == 0)
    {
        return "0"; // No unit for zero size
    }

    const char *units[] = {"B", "K", "M", "G", "T"};
    int unitIndex = 0;
    double readableSize = static_cast<double>(size * blockSize);

    while (readableSize >= 1024 && unitIndex < 4)
    {
        readableSize /= 1024;
        ++unitIndex;
    }

    std::ostringstream oss;
    oss << std::fixed << std::setprecision((readableSize < 10) ? 1 : 0)
        << readableSize << units[unitIndex];
    return oss.str();
}

bool parseArguments(int argc, char *argv[], std::map<std::string, bool> &flags, std::vector<std::string> &directories, int &blockSize)
{
    for (int i = 1; i < argc; ++i)
    {
        std::string arg = argv[i];

        // Check for multiplexed -b and -h flags
        if (arg.find("-bh") != std::string::npos || arg.find("-hb") != std::string::npos)
        {
            std::cout << "Error: cannot use both -b and -h\n";
            return false;
        }
        else if (arg == "-s")
            flags["-s"] = true;
        else if (arg == "-k")
        {
            flags["-k"] = true;
            blockSize = 1024;
        }
        else if (arg == "-h")
        {
            flags["-h"] = true;
            flags["-b"] = false; // Disable -b if -h is provided later
        }
        else if (arg == "-z")
        {
            flags["-z"] = true;
        }
        else if (arg == "-n")
        {
            flags["-n"] = true;
        }
        else if (arg == "-r")
            flags["-r"] = true;
        else if (arg == "-rz")
        {
            flags["-z"] = true;
            flags["-r"] = true;
        }
        else if (arg == "-rn")
        {
            flags["-n"] = true;
            flags["-r"] = true;
        }
        else if (arg == "-b")
        {
            flags["-b"] = true;
            flags["-h"] = false;
        }
        else if (arg == "--help")
            flags["--help"] = true;
        else if (arg == "--version")
            flags["--version"] = true;
        else if (arg.rfind("--block-size=", 0) == 0)
        {
            flags["--block-size"] = true;
            std::string blockSizeStr = arg.substr(13);
            try
            {
                blockSize = std::stoi(blockSizeStr);
                if (blockSize <= 0)
                {
                    std::cout << "Error: block size must be greater than 0\n";
                    return false;
                }
            }
            catch (const std::invalid_argument &)
            {
                std::cout << "Error: block-size value is invalid <" << blockSizeStr << ">\n";
                return false;
            }
            catch (const std::out_of_range &)
            {
                std::cout << "Error: block-size value is out of range <" << blockSizeStr << ">\n";
                return false;
            }
        }
        else if (arg == "-nz" || arg == "-zn")
        {
            flags["-n"] = true;
            flags["-z"] = true;
        }
        else if (arg[0] == '-')
        {
            std::cout << "Error: unknown switches: <" << arg.substr(1) << ">\n";
            return false;
        }
        else
        {
            directories.push_back(arg); // Store directory argument
        }

        if (flags["-z"] && flags["-n"])
        {
            std::cout << "Error: -n and -z switches are incompatible.\n";
            return false;
        }
    }

    if (flags["-k"] && flags["--block-size"])
    {
        std::cout << "Error: -k and --block-size are incompatible.\n";
        return false;
    }

    // If no directories were provided, default to current directory
    if (directories.empty())
    {
        directories.push_back(".");
    }

    return true;
}

void calculateDiskUsage(const std::string &directory, const std::map<std::string, bool> &flags, int blockSize)
{
    std::map<std::string, size_t> folderSizes;
    size_t rootTotalSize = 0;

    // Traverse only the root directory
    for (const auto &entry : std::filesystem::directory_iterator(directory))
    {
        if (entry.is_directory())
        {
            size_t totalSize = 0;
            for (const auto &file : std::filesystem::recursive_directory_iterator(entry))
            {
                if (file.is_regular_file())
                {
                    size_t fileSize = file.file_size();
                    size_t allocatedSize = ((fileSize + blockSize - 1) / blockSize);
                    allocatedSize = flags.at("-b") ? allocatedSize * blockSize : allocatedSize;
                    totalSize += allocatedSize;
                }
            }
            folderSizes[entry.path().string()] = totalSize;
            rootTotalSize += totalSize; // Include this subfolder's size in the root's total size
        }
        else if (entry.is_regular_file())
        {
            size_t fileSize = entry.file_size();
            size_t allocatedSize = ((fileSize + blockSize - 1) / blockSize);
            allocatedSize = flags.at("-b") ? allocatedSize * blockSize : allocatedSize;
            rootTotalSize += allocatedSize; // Include this file's size in the root's total size
        }
    }

    // If the -s flag is set, print only the total size and return
    if (flags.at("-s"))
    {
        std::cout << (flags.at("-h") ? humanReadableSize(rootTotalSize, blockSize) : std::to_string(rootTotalSize)) << "   " << directory << "\n";
        return;
    }

    // Ensure empty directories are shown
    for (const auto &entry : std::filesystem::directory_iterator(directory))
    {
        if (entry.is_directory() && folderSizes.find(entry.path().string()) == folderSizes.end())
        {
            folderSizes[entry.path().string()] = 0;
        }
    }

    std::vector<std::pair<std::string, size_t>> results(folderSizes.begin(), folderSizes.end());

    // Case-insensitive sorting by folder name
    std::sort(results.begin(), results.end(), [](const auto &a, const auto &b)
              {
            std::string lowerA = a.first;
            std::string lowerB = b.first;
            std::transform(lowerA.begin(), lowerA.end(), lowerA.begin(), ::tolower);
            std::transform(lowerB.begin(), lowerB.end(), lowerB.begin(), ::tolower);
            return lowerA < lowerB; });

    // Sorting by size if -z is set
    if (flags.at("-z"))
    {
        std::sort(results.begin(), results.end(), [](const auto &a, const auto &b)
                  { return a.second < b.second; });
    }

    // Add the root directory's total size
    results.emplace_back(directory, rootTotalSize);

    // Ascii sorting by folder name if -n is set

    if (flags.at("-n"))
    {
        // Case-sensitive (ASCII order)
        std::sort(results.begin(), results.end(), [](const auto &a, const auto &b)
                  { return a.first < b.first; });
    }

    // Reverse the order if -r is set
    if (flags.at("-r"))
    {
        std::reverse(results.begin(), results.end());
    }

    size_t maxSize = 0;
    for (const auto &[path, size] : results)
    {
        maxSize = std::max(maxSize, size);
    }
    int sizeWidth = (flags.at("-h") ? humanReadableSize(maxSize, blockSize).length() : std::to_string(maxSize).length());

    for (const auto &[path, size] : results)
    {
        std::cout << std::setw(sizeWidth) << (flags.at("-h") ? humanReadableSize(size, blockSize) : std::to_string(size))
                  << "   " << path << "\n";
    }
}
