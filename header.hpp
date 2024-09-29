#ifndef HEADER_HPP
#define HEADER_HPP

#include <iostream>
#include <iomanip>
#include <filesystem>
#include <map>
#include <vector>
#include <string>
#include <algorithm>
#include <sstream>

void displayHelp();
void displayVersion();
std::string humanReadableSize(size_t size, int &blockSize);
bool parseArguments(int argc, char *argv[], std::map<std::string, bool> &flags, std::vector<std::string> &directories, int &blockSize);
void calculateDiskUsage(const std::string &directory, const std::map<std::string, bool> &flags, int blockSize);

#endif
