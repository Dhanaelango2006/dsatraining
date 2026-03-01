// ================================================================
//   JAVA DATA STRUCTURES — Arrays, List, ArrayList, LinkedList
//   For Engineering Students | Real-World Use Cases + Complexity
// ================================================================
//   Compile:  javac DataStructuresList.java
//   Run:      java DataStructuresList
// ================================================================
//
//   WHAT IS A DATA STRUCTURE?
//   A way to ORGANIZE and STORE data so it can be accessed
//   and modified efficiently.
//
//   TOPICS COVERED:
//   1. 1D Array        — Fixed-size sequential storage
//   2. 2D Array        — Grid/matrix storage
//   3. List (interface)— Contract that ArrayList & LinkedList follow
//   4. ArrayList       — Dynamic resizable array
//   5. ArrayList vs LinkedList — When to use which
// ================================================================

import java.util.*;

public class DataStructuresList {

    static void section(String title) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.printf ("║  %-56s║%n", title);
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    static void sub(String title) {
        System.out.println("\n  ── " + title + " ──");
    }


    // ============================================================
    // SECTION 1 — 1D ARRAY
    // ============================================================
    //
    //   What is a 1D Array?
    //   A fixed-size, ordered collection of elements of the SAME type,
    //   stored in CONTIGUOUS (side-by-side) memory locations.
    //
    //   Memory layout for int[] scores = {90, 85, 78, 92, 88}:
    //   Index:   [ 0  ] [ 1  ] [ 2  ] [ 3  ] [ 4  ]
    //   Value:   [ 90 ] [ 85 ] [ 78 ] [ 92 ] [ 88 ]
    //   Address: [1000] [1004] [1008] [1012] [1016]  ← 4 bytes apart (int)
    //
    //   Because memory is contiguous, computing any element's address is:
    //   address(i) = base_address + (i × element_size)
    //   → This is why access is O(1) — it's just arithmetic!
    //
    //   REAL-WORLD USE CASE: Student exam scores system
    //   → Store scores for a fixed batch of 5 students
    //   → Find highest/lowest scorer
    //   → Compute class average
    //
    //   TIME COMPLEXITY:
    //   Access  arr[i]          → O(1)  direct address calculation
    //   Search  (unsorted)      → O(n)  may need to check every element
    //   Insert  (at end, fixed) → O(1)  just assign
    //   Insert  (at middle)     → O(n)  need to shift elements
    //   Delete  (at middle)     → O(n)  need to shift elements
    //
    //   SPACE COMPLEXITY: O(n) — n elements stored
    // ============================================================

    static void demo1DArray() {
        section("SECTION 1 — 1D ARRAY | Use Case: Student Exam Scores");

        // DECLARATION & INITIALIZATION
        int[] scores = {90, 85, 78, 92, 88};
        String[] names = {"Navaneeth", "Priya", "Ravi", "Ananya", "Karthik"};

        sub("All student scores");
        System.out.println("  Index    Name          Score");
        System.out.println("  ─────────────────────────────");
        for (int i = 0; i < scores.length; i++) {
            System.out.printf("  [%d]      %-12s  %d%n", i, names[i], scores[i]);
        }

        // O(1) ACCESS — direct index
        sub("O(1) Direct Access");
        System.out.println("  scores[0] = " + scores[0] + "  ← O(1): direct memory address, no searching");
        System.out.println("  scores[3] = " + scores[3] + "  ← O(1): same cost regardless of array size");

        // TRAVERSAL — O(n) to find max/min/sum
        sub("O(n) Traversal — Find Highest, Lowest, Average");
        int max = scores[0], maxIdx = 0;
        int min = scores[0], minIdx = 0;
        int sum = 0;

        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];                           // one pass through n elements
            if (scores[i] > max) { max = scores[i]; maxIdx = i; }
            if (scores[i] < min) { min = scores[i]; minIdx = i; }
        }
        double avg = (double) sum / scores.length;

        System.out.printf("  Highest : %s → %d (index %d)%n", names[maxIdx], max, maxIdx);
        System.out.printf("  Lowest  : %s → %d (index %d)%n", names[minIdx], min, minIdx);
        System.out.printf("  Average : %.1f%n", avg);
        System.out.println("  Operations: " + scores.length + " iterations → O(n)");

        // SORTING — Arrays.sort uses Dual-Pivot Quicksort → O(n log n)
        sub("O(n log n) Sort — Rank students by score");
        int[][] combined = new int[scores.length][2]; // [score][original_index]
        for (int i = 0; i < scores.length; i++) combined[i] = new int[]{scores[i], i};
        Arrays.sort(combined, (a, b) -> b[0] - a[0]); // sort descending by score

        System.out.println("  Rank  Name          Score");
        System.out.println("  ─────────────────────────────");
        for (int rank = 0; rank < combined.length; rank++) {
            System.out.printf("  #%d    %-12s  %d%n",
                    rank + 1, names[combined[rank][1]], combined[rank][0]);
        }

        // ARRAY LIMITATION
        sub("Array Limitation — Fixed Size");
        System.out.println("  scores = new int[5]  ← size fixed at creation");
        System.out.println("  Cannot add a 6th student without creating a NEW array.");
        System.out.println("  → This is exactly why ArrayList was invented.");

        // COMPLEXITY SUMMARY
        sub("Complexity Summary — 1D Array");
        System.out.println("  Operation          Time      Space   Reason");
        System.out.println("  ────────────────────────────────────────────────────────");
        System.out.println("  Access  arr[i]     O(1)      O(1)    Direct address calc");
        System.out.println("  Linear Search      O(n)      O(1)    Check each element");
        System.out.println("  Sort (Arrays.sort) O(n log n)O(1)    Dual-Pivot Quicksort");
        System.out.println("  Insert at middle   O(n)      O(1)    Must shift elements");
        System.out.println("  Overall storage    —         O(n)    n elements in memory");
    }


    // ============================================================
    // SECTION 2 — 2D ARRAY
    // ============================================================
    //
    //   What is a 2D Array?
    //   An array of arrays — creates a GRID/MATRIX structure.
    //   Think of it as a table with rows and columns.
    //
    //   Memory model for int[3][3]:
    //   grid[0] → [10, 20, 30]   ← row 0
    //   grid[1] → [40, 50, 60]   ← row 1
    //   grid[2] → [70, 80, 90]   ← row 2
    //
    //   Access: grid[row][col] → O(1) — two array lookups, still constant
    //
    //   REAL-WORLD USE CASE: Classroom seating + marks sheet
    //   → seats[row][col] = student seat assignment
    //   → marks[student][subject] = marks per subject per student
    //
    //   TIME COMPLEXITY:
    //   Access  grid[r][c]   → O(1)    two direct index lookups
    //   Full traversal       → O(r×c)  must visit every cell
    //   Row traversal        → O(c)    one row only
    //
    //   SPACE COMPLEXITY: O(r × c) — rows × columns cells
    // ============================================================

    static void demo2DArray() {
        section("SECTION 2 — 2D ARRAY | Use Case: Student Marks Sheet");

        String[] students = {"Navaneeth", "Priya", "Ravi"};
        String[] subjects = {"Maths", "Physics", "Java", "DSA"};

        // marks[student_index][subject_index]
        int[][] marks = {
            {88, 76, 95, 90},   // Navaneeth
            {72, 85, 80, 78},   // Priya
            {91, 68, 74, 85}    // Ravi
        };

        // DISPLAY THE FULL MARKS SHEET
        sub("Full Marks Sheet — O(r×c) traversal");
        System.out.printf("  %-12s", "Student");
        for (String sub : subjects) System.out.printf("  %-8s", sub);
        System.out.printf("  %-6s  %-5s%n", "Total", "Avg");
        System.out.println("  " + "─".repeat(62));

        // Nested loop: O(students × subjects) = O(r × c)
        for (int i = 0; i < students.length; i++) {
            System.out.printf("  %-12s", students[i]);
            int total = 0;
            for (int j = 0; j < subjects.length; j++) {
                System.out.printf("  %-8d", marks[i][j]);
                total += marks[i][j];
            }
            System.out.printf("  %-6d  %.1f%n", total, (double) total / subjects.length);
        }

        // O(1) ACCESS — specific cell
        sub("O(1) Direct Cell Access");
        System.out.println("  marks[0][2] = " + marks[0][2] + "  → Navaneeth's Java score (instant lookup)");
        System.out.println("  marks[2][0] = " + marks[2][0] + "  → Ravi's Maths score (instant lookup)");

        // COLUMN TRAVERSAL — subject-wise top scorer
        sub("O(r) Column Traversal — Top scorer per subject");
        for (int j = 0; j < subjects.length; j++) {
            int best = marks[0][j], bestIdx = 0;
            for (int i = 1; i < students.length; i++) {
                if (marks[i][j] > best) { best = marks[i][j]; bestIdx = i; }
            }
            System.out.printf("  %-8s → Top: %-12s (%d)%n", subjects[j], students[bestIdx], best);
        }

        // DEMONSTRATE JAGGED ARRAY (rows of different lengths)
        sub("Bonus — Jagged Array (rows of different lengths)");
        int[][] jagged = {
            {1},             // row 0: 1 element
            {2, 3},          // row 1: 2 elements
            {4, 5, 6},       // row 2: 3 elements
            {7, 8, 9, 10}    // row 3: 4 elements
        };
        System.out.println("  Jagged array (triangle shape):");
        for (int i = 0; i < jagged.length; i++) {
            System.out.print("  Row " + i + ": ");
            for (int val : jagged[i]) System.out.print(val + " ");
            System.out.println();
        }
        System.out.println("  Each row is a separate array — lengths can differ.");

        sub("Complexity Summary — 2D Array");
        System.out.println("  Operation             Time       Space   Reason");
        System.out.println("  ────────────────────────────────────────────────────────");
        System.out.println("  Access grid[r][c]     O(1)       O(1)    Two index lookups");
        System.out.println("  Full traversal        O(r×c)     O(1)    Every cell visited");
        System.out.println("  Row traversal         O(c)       O(1)    One row only");
        System.out.println("  Column traversal      O(r)       O(1)    One column only");
        System.out.println("  Total storage         —          O(r×c)  All cells in memory");
    }


    // ============================================================
    // SECTION 3 — LIST INTERFACE + ARRAYLIST
    // ============================================================
    //
    //   What is the List Interface?
    //   List is a CONTRACT (interface) in Java's Collections Framework.
    //   It defines WHAT operations a list must support:
    //     add(), get(), remove(), size(), contains(), indexOf(), etc.
    //   It does NOT define HOW they are implemented.
    //   ArrayList and LinkedList both implement List — different HOW.
    //
    //   List<String> list = new ArrayList<>();   ← program to the interface!
    //   List<String> list = new LinkedList<>();  ← swap easily, same API
    //
    //   What is ArrayList?
    //   A DYNAMIC array that grows automatically when full.
    //   Internally: Java allocates an array. When full, creates a new
    //   array 1.5× larger and copies everything over.
    //
    //   DEFAULT capacity = 10 elements
    //   When full → new capacity = old capacity × 1.5
    //   10 → 15 → 22 → 33 → ...
    //
    //   REAL-WORLD USE CASE: Food delivery app order history
    //   → Orders arrive dynamically — we don't know how many upfront
    //   → Need to add, view, search, and remove orders at any time
    //
    //   TIME COMPLEXITY:
    //   add(element)      → O(1) amortized  (O(n) when resizing occurs)
    //   add(index, elem)  → O(n)            shift elements right
    //   get(index)        → O(1)            direct index access
    //   remove(index)     → O(n)            shift elements left
    //   remove(object)    → O(n)            search first, then shift
    //   contains(object)  → O(n)            linear scan
    //   size()            → O(1)            stored separately
    //
    //   SPACE COMPLEXITY: O(n)
    //   Note: actual capacity ≥ size, so some memory is "pre-reserved"
    // ============================================================

    static void demoArrayList() {
        section("SECTION 3 — LIST & ARRAYLIST | Use Case: Food Delivery Orders");

        // List<T> is the interface. ArrayList<T> is the implementation.
        // Best practice: declare as List<>, not ArrayList<>
        List<String> orders = new ArrayList<>();

        sub("add() — O(1) amortized | New orders arriving");
        orders.add("Order#101 - Biryani from Behrouz");       // add at end
        orders.add("Order#102 - Pizza from Dominos");
        orders.add("Order#103 - Burger from McDonald's");
        orders.add("Order#104 - Sushi from Yo Sushi");
        orders.add("Order#105 - Dosa from Saravana Bhavan");

        System.out.println("  Orders received: " + orders.size());
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("  [" + i + "] " + orders.get(i));
        }

        sub("get(index) — O(1) | Customer checks order status");
        System.out.println("  orders.get(0) = " + orders.get(0) + "  ← instant, no iteration");
        System.out.println("  orders.get(3) = " + orders.get(3) + "  ← same O(1) cost");

        sub("add(index, element) — O(n) | Priority order inserted at front");
        orders.add(0, "Order#100 - PRIORITY: Medicine from pharmacy"); // shifts all right
        System.out.println("  Inserted priority order at index 0.");
        System.out.println("  All " + (orders.size() - 1) + " existing orders shifted right → O(n)");
        System.out.println("  [0] " + orders.get(0));
        System.out.println("  [1] " + orders.get(1) + "  ← shifted from index 0");

        sub("contains() — O(n) | Search for specific order");
        String searchTerm = "Pizza";
        boolean found = false;
        int foundIdx = -1;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).contains(searchTerm)) { found = true; foundIdx = i; break; }
        }
        System.out.println("  Searching for \"" + searchTerm + "\" → found: " + found +
                (found ? " at index " + foundIdx : ""));
        System.out.println("  Scanned up to " + (foundIdx + 1) + " elements → O(n)");

        sub("remove(index) — O(n) | Order cancelled");
        String cancelled = orders.remove(2); // removes and shifts left
        System.out.println("  Cancelled: " + cancelled);
        System.out.println("  All subsequent orders shifted left → O(n)");

        sub("Iteration — O(n) | End of day order report");
        System.out.println("  All active orders (" + orders.size() + " total):");
        for (String order : orders) { // enhanced for-loop iterates via Iterator
            System.out.println("    → " + order);
        }

        sub("Sorting — O(n log n) | Sort orders alphabetically");
        List<String> sortable = new ArrayList<>(orders);
        Collections.sort(sortable);
        System.out.println("  Sorted order list:");
        for (String order : sortable) System.out.println("    → " + order);

        sub("Collections utility methods — O(n)");
        System.out.println("  Total orders : " + orders.size());
        System.out.println("  Is empty?    : " + orders.isEmpty());
        orders.clear();
        System.out.println("  After clear(): " + orders.size() + " orders (O(n) to null all refs)");

        sub("Complexity Summary — ArrayList");
        System.out.println("  Operation            Time            Space   Note");
        System.out.println("  ──────────────────────────────────────────────────────────────");
        System.out.println("  add(element)         O(1) amortized  O(1)    O(n) when resize");
        System.out.println("  add(index, element)  O(n)            O(1)    Shifts right");
        System.out.println("  get(index)           O(1)            O(1)    Direct access");
        System.out.println("  remove(index)        O(n)            O(1)    Shifts left");
        System.out.println("  contains(object)     O(n)            O(1)    Linear scan");
        System.out.println("  size()               O(1)            O(1)    Stored as field");
        System.out.println("  sort()               O(n log n)      O(n)    TimSort");
        System.out.println("  Overall storage      —               O(n)    + unused capacity");
    }


    // ============================================================
    // SECTION 4 — ARRAYLIST vs LINKEDLIST
    // ============================================================
    //
    //   ArrayList  → backed by an array. Great for READING by index.
    //   LinkedList → backed by nodes with pointers. Great for INSERT/DELETE
    //                at front/middle (when you already have the node).
    //
    //   INTERNAL STRUCTURE:
    //
    //   ArrayList:
    //   [A][B][C][D][E]  ← contiguous memory
    //   Index 0,1,2,3,4
    //
    //   LinkedList:
    //   [null ← A → B] ↔ [A ← B → C] ↔ [B ← C → D] ↔ [C ← D → null]
    //   Each node stores: data + pointer to next + pointer to prev (doubly linked)
    //   Nodes can be ANYWHERE in memory — held together by pointers, not addresses
    //
    //   REAL-WORLD USE CASE:
    //   ArrayList  → Browser history (fast random access to nth page)
    //   LinkedList → Music playlist (frequent insertions/deletions mid-playlist)
    // ============================================================

    static void demoArrayListVsLinkedList() {
        section("SECTION 4 — ArrayList vs LinkedList | Glimpse & Comparison");

        // ── USE CASE: BROWSER HISTORY (ArrayList — frequent access by index)
        sub("Use Case A — Browser History (ArrayList)");
        List<String> browserHistory = new ArrayList<>();
        browserHistory.add("google.com");
        browserHistory.add("claude.ai");
        browserHistory.add("stackoverflow.com");
        browserHistory.add("github.com");
        browserHistory.add("youtube.com");

        System.out.println("  Visited pages:");
        for (int i = 0; i < browserHistory.size(); i++) {
            System.out.println("  [" + i + "] " + browserHistory.get(i));
        }

        // Random access — the killer feature of ArrayList
        System.out.println("\n  Go back 2 pages: " + browserHistory.get(browserHistory.size() - 3));
        System.out.println("  Jump to page 1 : " + browserHistory.get(1) + "  ← O(1) — ArrayList excels here");

        // ── USE CASE: MUSIC PLAYLIST (LinkedList — frequent add/remove)
        sub("Use Case B — Music Playlist (LinkedList)");
        LinkedList<String> playlist = new LinkedList<>();
        playlist.add("Blinding Lights - The Weeknd");
        playlist.add("Shape of You - Ed Sheeran");
        playlist.add("Levitating - Dua Lipa");

        System.out.println("  Current playlist:");
        playlist.forEach(song -> System.out.println("    ♪ " + song));

        // addFirst / addLast / addAfter — LinkedList strengths
        playlist.addFirst("Bohemian Rhapsody - Queen");    // O(1) — just update head pointer
        playlist.addLast("Believer - Imagine Dragons");    // O(1) — just update tail pointer

        System.out.println("\n  After adding to front and back:");
        playlist.forEach(song -> System.out.println("    ♪ " + song));

        playlist.removeFirst(); // O(1) — just update head pointer
        System.out.println("\n  After removing first song:");
        playlist.forEach(song -> System.out.println("    ♪ " + song));

        // ── PERFORMANCE TIMING — measure actual difference
        sub("Live Performance Timing — 100,000 operations");

        int N = 100_000;

        // Test 1: get() by index — ArrayList should win
        List<Integer> arrayList   = new ArrayList<>();
        List<Integer> linkedList  = new LinkedList<>();
        for (int i = 0; i < N; i++) { arrayList.add(i); linkedList.add(i); }

        long t1 = System.nanoTime();
        for (int i = 0; i < N; i++) arrayList.get(i % arrayList.size());
        long arrayGetTime = (System.nanoTime() - t1) / 1_000_000;

        long t2 = System.nanoTime();
        for (int i = 0; i < 1000; i++) ((LinkedList<Integer>)linkedList).get(i % 1000);
        long linkedGetTime = (System.nanoTime() - t2) / 1_000_000;

        System.out.println("  get(index) — random access [100K elements]:");
        System.out.printf("    ArrayList   → %d ms  (O(1) per call)%n", arrayGetTime);
        System.out.printf("    LinkedList  → %d ms  (O(n) per call — traverses from head!)%n", linkedGetTime);

        // Test 2: addFirst() — LinkedList should win
        List<Integer> alAdd = new ArrayList<>();
        LinkedList<Integer> llAdd = new LinkedList<>();

        long t3 = System.nanoTime();
        for (int i = 0; i < N; i++) alAdd.add(0, i);   // insert at front
        long alAddTime = (System.nanoTime() - t3) / 1_000_000;

        long t4 = System.nanoTime();
        for (int i = 0; i < N; i++) llAdd.addFirst(i);  // insert at front
        long llAddTime = (System.nanoTime() - t4) / 1_000_000;

        System.out.println("\n  addFirst() — insert at front [100K operations]:");
        System.out.printf("    ArrayList   → %d ms  (O(n) per call — shifts everything right!)%n", alAddTime);
        System.out.printf("    LinkedList  → %d ms  (O(1) per call — just update head pointer)%n", llAddTime);

        // ── COMPARISON TABLE
        sub("Head-to-Head Comparison");
        System.out.println("  ┌────────────────────────┬──────────────┬──────────────┐");
        System.out.println("  │  Operation             │  ArrayList   │  LinkedList  │");
        System.out.println("  ├────────────────────────┼──────────────┼──────────────┤");
        System.out.println("  │  get(index)            │  O(1) ✅    │  O(n) ❌    │");
        System.out.println("  │  add(element) at end   │  O(1) amort │  O(1)        │");
        System.out.println("  │  add(0, elem) at front │  O(n) ❌    │  O(1) ✅    │");
        System.out.println("  │  remove(index)         │  O(n) ❌    │  O(n)*       │");
        System.out.println("  │  remove first/last     │  O(n) ❌    │  O(1) ✅    │");
        System.out.println("  │  contains()            │  O(n)        │  O(n)        │");
        System.out.println("  │  Memory per element    │  ~4 bytes    │  ~20 bytes   │");
        System.out.println("  │  Cache friendly?       │  Yes ✅     │  No ❌      │");
        System.out.println("  ├────────────────────────┼──────────────┼──────────────┤");
        System.out.println("  │  Best for              │  Reading     │  Inserting/  │");
        System.out.println("  │                        │  by index    │  Deleting at │");
        System.out.println("  │                        │  Iteration   │  front/back  │");
        System.out.println("  └────────────────────────┴──────────────┴──────────────┘");
        System.out.println("  * LinkedList remove(index) is O(n) because it must TRAVERSE to find the node.");
        System.out.println("    But if you already HAVE the node (via Iterator), removal is O(1).");

        sub("The Golden Rule");
        System.out.println("  Use ArrayList  → when you READ often (get by index, iterate)");
        System.out.println("  Use LinkedList → when you INSERT/DELETE at front/back frequently");
        System.out.println("  In doubt?      → ArrayList wins in 90% of real-world cases");
        System.out.println("                   (better cache performance in modern hardware)");
    }


    // ============================================================
    //   MAIN
    // ============================================================
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   JAVA DATA STRUCTURES — Arrays, List, ArrayList        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        demo1DArray();
        demo2DArray();
        demoArrayList();
        demoArrayListVsLinkedList();

        // ── FINAL OVERVIEW
        section("FINAL SUMMARY — All Data Structures at a Glance");
        System.out.println();
        System.out.println("  ┌──────────────┬──────────┬──────────┬──────────┬───────────────────────────┐");
        System.out.println("  │  Structure   │  Access  │  Insert  │  Delete  │  Best Real-World Use      │");
        System.out.println("  ├──────────────┼──────────┼──────────┼──────────┼───────────────────────────┤");
        System.out.println("  │  1D Array    │  O(1)    │  O(n)    │  O(n)    │  Fixed-size score sheets  │");
        System.out.println("  │  2D Array    │  O(1)    │  O(n²)   │  O(n²)   │  Grids, matrices, seats   │");
        System.out.println("  │  ArrayList   │  O(1)    │  O(1)*   │  O(n)    │  Dynamic lists, orders    │");
        System.out.println("  │  LinkedList  │  O(n)    │  O(1)**  │  O(1)**  │  Playlists, queues        │");
        System.out.println("  └──────────────┴──────────┴──────────┴──────────┴───────────────────────────┘");
        System.out.println("  * ArrayList add() is O(1) amortized (O(n) when internal array resizes)");
        System.out.println("  ** LinkedList insert/delete is O(1) only at head/tail or with a known node");
    }
}
