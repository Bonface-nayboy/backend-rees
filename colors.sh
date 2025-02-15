#!/bin/bash
echo "This is colors.sh"
echo -e "\e[32mThis is green text\e[0m"
tput setaf 2  # Changes text color (remove this)
tput sgr0     # Resets formatting (remove this)

echo -e "\e[32mThis is green text\e[0m"
#!/bin/bash
echo "This is colors.sh"
echo -e "\e[32mThis is green text\e[0m"  # Green text using ANSI escape codes





