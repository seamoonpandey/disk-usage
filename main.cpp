#include <iostream>
#include <filesystem>
#include <map>
#include <vector>
#include <algorithm>
#include <iomanip>
#include <sstream>

// Function to display help message
void displayHelp()
{
    std::cout << "Usage: du [-skhzrnrzrnb] [--help] [--version] [--block-size=ddd] [folder]*\n";
    std::cout << "Options:\n";
    std::cout << "  -s            Display only the final summary of each root folder\n";
    std::cout << "  -k            Set the cluster size to 1024 bytes\n";
    std::cout << "  -h            Human-readable output in KiB, MiB, GiB, TiB\n";
    std::cout << "  -z            Sort rows by size\n";
    std::cout << "  -n            Sort rows by folder name\n";
    std::cout << "  -r            Reverse the order of the output\n";
    std::cout << "  -b            Output in bytes, not clusters\n";
    std::cout << "  -rz           Sort rows by size in reverse order\n";
    std::cout << "  -rn           Sort rows by folder name in reverse order\n";
    std::cout << "  --help        Display this help and exit\n";
    std::cout << "  --version     Display version information and exit\n";
    std::cout << "  --block-size=ddd  Set the cluster size to the specified value\n";
}

// Function to display version information
void displayVersion()
{
    std::cout << "du version 1.0.0\n";
}

// Function to convert size to human-readable format
std::string humanReadableSize(size_t size)
{
    const char *units[] = {"B", "KiB", "MiB", "GiB", "TiB"};
    int unitIndex = 0;
    double readableSize = static_cast<double>(size);

    while (readableSize >= 1024 && unitIndex < 4)
    {
        readableSize /= 1024;
        ++unitIndex;
    }

    std::ostringstream oss;
    oss << std::fixed << std::setprecision((readableSize < 10) ? 1 : 0)
        << readableSize << ' ' << units[unitIndex];
    return oss.str();
}

// Function to parse command-line arguments
bool parseArguments(int argc, char *argv[], std::map<std::string, bool> &flags, std::string &directory, int &blockSize)
{
    for (int i = 1; i < argc; ++i)
    {
        std::string arg = argv[i];
        if (arg == "-s")
            flags["-s"] = true;
        else if (arg == "-k")
        {
            flags["-k"] = true;
            blockSize = 1024;
        }
        else if (arg == "-h")
            flags["-h"] = true;
        else if (arg == "-z")
            flags["-z"] = true;
        else if (arg == "-n")
            flags["-n"] = true;
        else if (arg == "-r")
            flags["-r"] = true;
        else if (arg == "-b")
            flags["-b"] = true;
        else if (arg == "-rz")
            flags["-rz"] = true;
        else if (arg == "-rn")
            flags["-rn"] = true;
        else if (arg == "--help")
            flags["--help"] = true;
        else if (arg == "--version")
            flags["--version"] = true;
        else if (arg.rfind("--block-size=", 0) == 0)
        {
            flags["--block-size"] = true;
            blockSize = std::stoi(arg.substr(13));
            if (blockSize <= 0)
            {
                std::cerr << "Error: block size must be greater than 0\n";
                return false;
            }
        }
        else if (arg[0] == '-')
        {
            std::cerr << "Error: unknown or incompatible switch: " << arg << "\n";
            return false;
        }
        else
        {
            directory = arg;
        }
    }

    // Rule enforcement
    if (flags["-b"] && flags["-h"])
    {
        std::cerr << "Error: -b and -h switches are incompatible\n";
        return false;
    }
    if (flags["-k"] && flags["--block-size"])
    {
        std::cerr << "Error: -k and --block-size switches are incompatible\n";
        return false;
    }
    if ((flags["-z"] && flags["-n"]) || (flags["-rz"] && flags["-rn"]))
    {
        std::cerr << "Error: -z and -n or -rz and -rn switches are incompatible\n";
        return false;
    }

    return true;
}

// Function to calculate and print disk usage for a directory
void calculateDiskUsage(const std::string &directory, const std::map<std::string, bool> &flags, int blockSize)
{
    size_t totalSize = 0;
    std::vector<std::pair<std::string, size_t>> results;

    for (const auto &entry : std::filesystem::recursive_directory_iterator(directory))
    {
        if (entry.is_regular_file())
        {
            size_t fileSize = entry.file_size();
            size_t allocatedSize = ((fileSize + blockSize - 1) / blockSize) * blockSize;
            totalSize += allocatedSize;
            results.emplace_back(entry.path().string(), allocatedSize);
        }
        else if (entry.is_directory())
        {
            results.emplace_back(entry.path().string(), 0); // Include empty directories
        }
    }

    // Sorting based on the flags
    if (flags.at("-z") || flags.at("-rz"))
    {
        std::sort(results.begin(), results.end(), [](const auto &a, const auto &b)
                  { return a.second < b.second; });
        if (flags.at("-rz"))
            std::reverse(results.begin(), results.end());
    }
    else if (flags.at("-n") || flags.at("-rn"))
    {
        std::sort(results.begin(), results.end());
        if (flags.at("-rn"))
            std::reverse(results.begin(), results.end());
    }

    // Determine the maximum size value for dynamic column width
    size_t maxSize = 0;
    for (const auto &[path, size] : results)
    {
        maxSize = std::max(maxSize, size);
    }
    int sizeWidth = (flags.at("-h") ? humanReadableSize(maxSize).length() : std::to_string(maxSize).length());

    // Output results
    for (const auto &[path, size] : results)
    {
        std::cout << std::setw(sizeWidth) << (flags.at("-h") ? humanReadableSize(size) : std::to_string(size))
                  << "   " << path << "\n";
    }

    // Display summary if -s flag is set
    if (flags.at("-s"))
    {
        std::cout << "\nTotal: " << std::setw(sizeWidth)
                  << (flags.at("-h") ? humanReadableSize(totalSize) : std::to_string(totalSize)) << "\n";
    }
}

int main(int argc, char *argv[])
{
    std::map<std::string, bool> flags = {
        {"-s", false}, {"-k", false}, {"-h", false}, {"-z", false}, {"-n", false}, {"-r", false}, {"-b", false}, {"-rz", false}, {"-rn", false}, {"--help", false}, {"--version", false}, {"--block-size", false}};
    std::string directory = ".";
    int blockSize = 512;

    if (!parseArguments(argc, argv, flags, directory, blockSize))
    {
        return 1; // Error in arguments
    }

    if (flags["--help"])
    {
        displayHelp();
        return 0;
    }

    if (flags["--version"])
    {
        displayVersion();
        return 0;
    }

    calculateDiskUsage(directory, flags, blockSize);

    return 0;
}
