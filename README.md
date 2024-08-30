🌟 Dynamic Image Overlay with Price Fetching 🚀

Overview ✨
This project is a powerful Java-based tool that dynamically overlays transparent product images onto background images, while preserving transparency. It seamlessly integrates with the Amazon Price API to fetch real-time product prices based on ASINs (Amazon Standard Identification Numbers). The final images, complete with item overlays and pricing details, are saved in the finalImages directory.

++++ IDE - Visual Studio Code +++

Features 💡
🔍 Transparent Image Handling: Effortlessly overlay transparent PNG images while maintaining their transparency.
💸 Automated Price Fetching: Fetch product prices from the Amazon Price API using ASINs derived directly from image file names.
🛠️ Customizable Workflow: Easily adapt the project to different backgrounds, item images, and API configurations.
How It Works 🛠️
Backgrounds: Add your background images (JPG/PNG) to the backgrounds folder.
Items: Place your transparent PNG product images, named after their respective ASINs, in the items folder.
Execution: Run the Java program. It will:
Overlay each item image onto the background.
Fetch the corresponding product price using the ASIN.
Save the final composite images in the finalImages folder.
Prerequisites 📦
Java (JDK 8 or higher)
Dependencies:
okhttp-5.0.0-alpha.14.jar
gson-2.8.6.jar
okio-jvm-3.9.0.jar
kotlin-stdlib-1.9.23.jar
Installation & Usage 🚀
Clone the Repository:


Compile the Project:

bash
Copy code
javac -cp .:okhttp-5.0.0-alpha.14.jar:gson-2.8.6.jar:okio-jvm-3.9.0.jar:kotlin-stdlib-1.9.23.jar DynamicImageOverlay.java

Run the Program:

bash
Copy code
java -cp .:okhttp-5.0.0-alpha.14.jar:gson-2.8.6.jar:okio-jvm-3.9.0.jar:kotlin-stdlib-1.9.23.jar DynamicImageOverlay

Output 🎨
The generated images with overlaid products and prices will be saved in the finalImages folder, ready for use!

License 📄
This project is licensed under the MIT License. See the LICENSE file for details.
