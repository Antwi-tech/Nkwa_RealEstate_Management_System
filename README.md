# 📘 Nkwa Real Estate Expenditure Management System

An offline-first, command-line Java application designed by Nkwa Real Estate Ltd to efficiently **track expenditures**, **manage bank accounts**, **categorize costs**, **handle receipts/invoices**, and generate **financial reports**, all using **vanilla Java** and **custom-built data structures** without external libraries ([github.com][1]).

---

## ✅ Main Features

* **Expenditure Management**
  Record and view expenditures by code, amount, date, phase, category, and associated bank account.

* **Bank Account Management**
  Maintain multiple bank accounts with balance tracking and allow bank-based operations.

* **Category Management**
  Dynamically manage spending categories with enforcement of uniqueness.

* **Receipt Handling**
  Upload and review receipt/invoice documents, simulating an accounting approval workflow.

* **Search & Sort**
  Filter expenditures by date, category, account, or other properties; sort with custom algorithms.

* **Financial Reports & Analytics**
  Calculate monthly burn rates, profitability forecasts, and category-level cost impact.

* **Custom Data Structures**
  Includes home-grown implementations of HashMap, HashSet, LinkedList, ArrayList, Stack, PriorityQueue (Min-Heap), TreeMap, and more.

* **File-Based Persistence**
  All data is stored in local text files (`expenditures.txt`, `accounts.txt`, `category.txt`, `receipts.txt`) for offline use.

---

## 📁 Project Structure

```
src/
└── Menu/
    ├── addExpenditure.java
    ├── viewExpenditure.java
    ├── bankAccount.java
    ├── manageCategories.java
    ├── receipt.java
    ├── reportAnalysis.java
└── DSA/
    ├── hashmap.java
    ├── hashset.java
    ├── linkedList.java
    ├── arrayList.java
    ├── stack.java
    ├── priorityqueue.java
    ├── myTreeMap.java
└── Expenditure_Management_System.java
data_files/ (text-based storage)
```

Each `Menu/` file corresponds to a user-facing feature, while `DSA/` hosts the core data structures.

---

## 🧰 How Data Structures Are Used

| Feature                 | Data Structure                                                | Purpose                                                 |
| ----------------------- | ------------------------------------------------------------- | ------------------------------------------------------- |
| **Expenditure storage** | `HashMap<String, Expenditure>`                                | Constant-time insert/lookup by code                     |
| **History listing**     | `LinkedList<String>`                                          | Preserves insertion order of expenditures               |
| **Category set**        | `HashSet<String>`                                             | Ensures unique categories and fast containment checks   |
| **Receipt queue**       | `Stack<Receipt>`                                              | LIFO order to review uploaded receipts                  |
| **Bank balances**       | `HashMap<String, BankAccount>` & `PriorityQueue<BankAccount>` | Fast account lookup and alerting for low balances       |
| **Sorting by category** | `ArrayList<String>` + Bubble sort                             | Simplicity for small/excluded datasets                  |
| **Monthly reports**     | `TreeMap<YearMonth, Double>`                                  | Automatically sorted by month for burn-rate aggregation |

### Algorithm Highlights

* **Sorting (Bubble Sort)**

    * Used for user-facing sorting tasks.
    * **Complexity**: O(n²) in worst-case; acceptable for few hundred entries.

* **Searching**

    * `HashMap.get()` for code/account lookups — O(1) average.
    * Category & account filters scan lists — O(n).

* **Aggregation**

    * `TreeMap` is implemented via a BST that aggregates by month in O(log n) per insertion on average, and O(n) for in-order traversal.

---

## 📊 Complexity Analysis

* **HashMap insert/get**:

    * *Time*: O(1) average, O(n) worst-case
    * *Space*: O(n)

* **HashSet add/contains**:

    * *Time*: O(1) average, O(n) worst-case
    * *Space*: O(n)

* **LinkedList addLast**:

    * *Time*: O(n) (could be improved with tail pointer)
    * *Space*: O(n)

* **ArrayList + Bubble Sort**:

    * *Time*: O(n²) in all cases
    * *Space*: O(n)

* **Stack push/pop**:

    * *Time*: O(1)

* **PriorityQueue add/poll (Min‑Heap)**:

    * *Time*: O(log n) per operation

* **TreeMap insert/traverse**:

    * *Time*: O(log n) average for insert, O(n) for full in-order traversal

---

## 🛠️ Getting Started

1. **Clone** the repository.
2. **Compile** all `.java` files:

   ```bash
   javac -d out src/**/*.java
   ```
3. **Run** the main class:

   ```bash
   java -cp out Expenditure_Management_System
   ```
4. **Interact** via the menu-driven CLI. View/write data in the `Menu/` directory files.

---

## ✍️ Author & Contact

Developed by Nkwa Real Estate Ltd (Jephthah Antwi and team) for offline-capable financial tracking and analysis — built for affordability and technical transparency.

Here’s the **updated README** section with the **DSA performance results** you provided, including a detailed explanation of what they imply about your system's efficiency and use of custom data structures:

---

## 🧪 📊 DSA Performance Benchmark

The system includes an in-built **performance testing module** (`DSAPerformanceTest`) that benchmarks the custom data structures used throughout the application.

### ⚙️ Benchmark Results (10,000 Elements Unless Noted)

| Data Structure  | Operation             | Time (ms)  |
| --------------- | --------------------- | ---------- |
| `arrayList`     | Bubble Sort (1k ints) | 19.4600 ms |
| `linkedList`    | Add Last              | 11.7619 ms |
| `hashmap`       | Put                   | 43.4005 ms |
| `hashmap`       | Get                   | 36.3863 ms |
| `hashset`       | Add                   | 47.0410 ms |
| `stack`         | Push                  | 1.1727 ms  |
| `stack`         | Pop                   | 0.4890 ms  |
| `priorityqueue` | Add                   | 4.7483 ms  |
| `priorityqueue` | Poll                  | 9.0544 ms  |
| `treeMap`       | Put / Update          | 7.0250 ms  |

---

### 📌 What This Tells Us

These benchmarks measure **how quickly your custom-built data structures** handle large-scale data operations (10,000 items), giving insight into **time complexity**, **implementation efficiency**, and **real-world performance** in the application’s core functionality.

#### 🔍 Interpretation

| Structure                    | Efficiency Verdict                   | Reason                                                                                            |
| ---------------------------- | ------------------------------------ | ------------------------------------------------------------------------------------------------- |
| **ArrayList + Bubble Sort**  | 🚫 **Inefficient for large sorting** | O(n²) bubble sort is slow for larger datasets; suitable only for small menus or short reports.    |
| **LinkedList Add**           | ✅ Acceptable                         | O(n), but for 10k operations, 11ms is decent due to lightweight node management.                  |
| **HashMap Get/Put**          | ✅ Reasonable                         | Slightly slower than expected (\~40ms), possibly due to primitive hashing or collision handling.  |
| **HashSet Add**              | ⚠️ Could Be Improved                 | Add performance close to HashMap; may be suffering from hash collision resolution inefficiencies. |
| **Stack Push/Pop**           | 💯 Excellent                         | Constant-time operations showing sub-millisecond results.                                         |
| **PriorityQueue (Min-Heap)** | ✅ Very Efficient                     | Add and poll remain within logarithmic bounds (\~4-9ms).                                          |
| **TreeMap (by Month)**       | 💡 **Very Efficient**                | Only 7ms for 10k monthly entries with update logic — great for reporting & trend analytics.       |

---

### 🔍 Summary

The application performs reliably with custom-built DSA logic and holds up under realistic data loads. Here’s how the performance maps to real functionality:

* 📂 **Menu Sorting/Filtering** → Uses `arrayList` and `bubbleSort`: fine for small menus but not for big logs.
* 🧾 **Expenditure History** → `linkedList` for efficient sequential insertions.
* 🔐 **Accounts & Expenditure Lookup** → `hashmap` for fast lookups of account codes and transactions.
* 📂 **Unique Categories** → `hashset` to prevent duplicates efficiently.
* 📸 **Receipts (LIFO)** → `stack` used to review uploads in reverse order.
* 💰 **Low Balance Alerts** → `priorityqueue` used to prioritize accounts with lowest balances.
* 📈 **Burn Rate/Monthly Reports** → `treeMap` organizes and aggregates costs per `YearMonth`.

---

