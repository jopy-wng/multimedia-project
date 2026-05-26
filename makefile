SRC_DIR := src
BIN_DIR := bin

all:
	mkdir -p $(BIN_DIR)
	javac -d $(BIN_DIR) $(SRC_DIR)/*.java

run: all
	java -cp $(BIN_DIR) Game