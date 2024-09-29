#include "header.hpp"

int main(int argc, char *argv[])
{
    std::map<std::string, bool> flags{
        {"-s", false}, {"-k", false}, {"-h", false}, {"-z", false}, {"-n", false}, {"-r", false}, {"-b", false}, {"--help", false}, {"--version", false}, {"--block-size", false}};
    std::vector<std::string> directories;
    int blockSize = 4096;

    if (!parseArguments(argc, argv, flags, directories, blockSize))
    {
        return 1;
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

    for (const std::string &directory : directories)
    { // Use const reference
        calculateDiskUsage(directory, flags, blockSize);
    }

    return 0;
}
