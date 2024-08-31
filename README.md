# Image Overlay Project using MS Excel (Without API)

![Project Logo](https://via.placeholder.com/150)  <!-- Add an image link if you have a project logo -->

## Overview
This Java project overlays item images onto background images, with item prices fetched from a local Excel file (`item_prices.xlsx`) instead of using APIs. The project is designed to work with transparent images and supports customization of item images and backgrounds.

## Features
- **Overlay Transparent Images**: Automatically scales and positions transparent item images onto backgrounds.
- **Fetch Prices from Excel**: Reads item names and prices from `item_prices.xlsx`.
- **Customizable**: Allows users to change item and background images.
- **Uses Apache POI**: Leverages Apache POI libraries to interact with Excel files.

## Requirements
- **Java 8 or higher**
- **Apache POI 4.1.2**
- **Required JARs**:
  - `poi-4.1.2.jar`
  - `poi-ooxml-4.1.2.jar`
  - `poi-ooxml-schemas-4.1.2.jar`
  - `xmlbeans-3.1.0.jar`
  - `commons-io-2.8.0.jar`
  - `commons-collections4-4.4.jar`
  - `log4j-api-2.23.1.jar`
  - `log4j-core-2.23.1.jar`
  - `commons-compress-1.18.jar`

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/Pathum-Chamod/ImageOverlayProject.git
    ```

2. Navigate to the project directory:
    ```bash
    cd ImageOverlayProject
    ```

3. Compile the project:
    ```bash
    javac -cp .:poi-4.1.2.jar:poi-ooxml-4.1.2.jar:poi-ooxml-schemas-4.1.2.jar:xmlbeans-3.1.0.jar:commons-io-2.8.0.jar:commons-collections4-4.4.jar:log4j-api-2.23.1.jar:log4j-core-2.23.1.jar:commons-compress-1.18.jar DynamicImageOverlay.java
    ```

4. Run the project:
    ```bash
    java -cp .:poi-4.1.2.jar:poi-ooxml-4.1.2.jar:poi-ooxml-schemas-4.1.2.jar:xmlbeans-3.1.0.jar:commons-io-2.8.0.jar:commons-collections4-4.4.jar:log4j-api-2.23.1.jar:log4j-core-2.23.1.jar:commons-compress-1.18.jar DynamicImageOverlay
    ```

## Usage
- **Background Images**: Add your background images to the `backgrounds/` folder.
- **Item Images**: Add your item images to the `items/` folder. Ensure the item image filenames match the item names in the `item_prices.xlsx` file.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
This project is licensed under the MIT License.

---

Feel free to reach out if you have any questions or need further assistance.
