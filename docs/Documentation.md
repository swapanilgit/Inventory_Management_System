# 📦 Inventory Management System (Java)

## 📌 Project Overview
This project is a **Java-based Inventory Management System** that uses the **Java Collections Framework** to efficiently manage products, track transactions, and perform inventory operations.

The system supports:
- Product management
- Sorting & searching
- Transaction tracking
- Undo functionality
- Low stock alerts
- File persistence

---

## 🎯 Objectives
- Manage inventory efficiently using data structures
- Ensure unique product identification (SKU)
- Implement sorting and searching algorithms
- Track inventory transactions
- Provide undo functionality
- Generate low stock alerts
- Demonstrate real-world use of collections

---

## 🛠️ Technologies Used
- Java
- Java Collections Framework
- File Handling (I/O)

---

## 🧱 Data Structures Used

| Data Structure | Purpose | Reason |
|---------------|--------|--------|
| HashSet | Store unique products | Prevent duplicates |
| TreeSet / Sorting | Sorted product display | Ordered data |
| LinkedList | Transaction history | Fast insert/delete |
| Stack | Undo operations | LIFO principle |
| Queue | Low stock alerts | FIFO principle |

---

## 🏗️ Project Structure
    src/com/inventory/
    │
    ├── model/
    │ ├── Product.java
    │ └── Transaction.java
    │
    ├── comparators/
    │ ├── PriceComparator.java
    │ ├── ValueComparator.java
    │ └── NameComparator.java
    │
    ├── collections/
    │ └── InventoryManagementSystem.java
    │
    ├── utils/
    │ └── InventoryUtils.java
    │
    └── Main.java
---

## 🧩 Class Description

### 1. Product Class
- Stores product details: SKU, Name, Category, Price, Quantity
- Implements `Comparable` (sorting by SKU)
- Overrides `equals()` and `hashCode()`

### 2. InventoryManagementSystem Class
- Core logic of the system
- Handles all operations:
  - Add product
  - Update quantity
  - Search
  - Display
  - Undo
  - Transactions

### 3. Comparator Classes
- `PriceComparator` → Sort by price  
- `ValueComparator` → Sort by inventory value  
- `NameComparator` → Sort by product name  

### 4. Utility Class
- Generic search method for reusable logic

### 5. Main Class
- Menu-driven interface
- Handles user input and program flow

---

## ⚙️ Features

- ✅ Add Product  
- ✅ Update Product Quantity  
- ✅ Display Products (sorted by SKU, price, name, value)  
- ✅ Search Products (keyword-based)  
- ✅ Low Stock Alerts (quantity < 10)  
- ✅ Transaction History  
- ✅ Undo Last Operation  
- ✅ File Save & Load  

---

## 🔍 Algorithms Used

### 1. Searching
- Linear Search  
- Time Complexity: **O(n)**  

### 2. Sorting
- Java `Collections.sort()`  
- Time Complexity: **O(n log n)**  

### 3. Undo Mechanism
- Stack-based implementation  

---

## 💾 File Handling

- Data stored in: `data/sample-data.txt`
- Format:
    SKU,Name,Category,Price,Quantity

### Workflow:
- On start → Load data from file  
- During execution → Modify in memory  
- On exit → Save data to file  

---

## 📊 Sample Output

- Product added successfully  
- Low stock alert displayed  
- Sorted product list shown  
- Search results displayed  
- Transaction history printed  
- Undo operation executed  

---

## ✅ Advantages

- Efficient data management  
- Prevents duplicate entries  
- Fast operations using collections  
- Scalable and modular design  
- Real-world application simulation  

---

## ⚠️ Limitations

- Command-line interface (no GUI)  
- File-based storage (no database)  
- Basic validation  

---

## 🚀 Future Enhancements

- Add GUI (Java Swing / JavaFX)  
- Database integration (MySQL)  
- User authentication system  
- Advanced reporting & analytics  
- Barcode scanning support  

---

## 📌 Conclusion

This project demonstrates the effective use of the **Java Collections Framework** to build a scalable and efficient inventory system. It simulates real-world inventory operations and provides a strong foundation for further enhancements.

---

## 👨‍💻 Author
    Swapanil Gupta