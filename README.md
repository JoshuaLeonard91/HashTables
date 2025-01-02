# Hash Table Project

This project implements a **Hash Table** using **Separate Chaining** to manage a collection of countries and their corresponding data. The data is read from a CSV file (`Countries5.csv`), which contains information about countries such as population, COVID cases, GDP, and area. The program allows users to insert, delete, search, and display information in the hash table.

## Features

- **Insert**: Adds a country to the hash table.
- **Delete**: Removes a country from the hash table.
- **Search**: Finds a country and displays its case rate.
- **Display**: Prints the hash table with country names and their case rates.
- **Empty and Collided Cells**: Prints the number of empty cells and collided cells in the hash table.

## Classes

### `HashTable`

The `HashTable` class contains the hash table implementation using **separate chaining** with a singly linked list. The class supports the following methods:

- **Constructor**: Initializes an empty hash table.
- **insert**: Inserts a country, its population, and cases into the hash table.
- **find**: Searches for a country by name and returns the table index or `-1` if not found.
- **delete**: Deletes a country from the hash table by name.
- **display**: Displays the hash table with countries and their case rates.
- **printEmptyAndCollidedCells**: Prints the number of empty and collided cells in the hash table.

### `Projects`

The `Projects` class serves as the user interface and allows users to:

- Input a CSV file (`Countries5.csv`) to populate the hash table.
- Perform the following actions:
  - Print the hash table.
  - Delete a country.
  - Insert a country.
  - Search for a country and display its case rate.
  - Print the number of empty and collided cells.
  - Exit the program.

## CSV File

The `Countries5.csv` file contains the following columns:

- **Country Name**: The name of the country.
- **Capital City**: The capital of the country.
- **Population (2020)**: The population of the country in 2020.
- **GDP (2020)**: The Gross Domestic Product in USD.
- **COVID Total Cases (2022)**: The total COVID-19 cases as of August 2022.
- **Area (km²)**: The total area of the country in square kilometers.
