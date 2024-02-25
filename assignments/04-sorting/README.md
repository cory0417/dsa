# Sorting Algorithm Performance Analysis

## Testing Methodology

To evaluate the performance of four well-known sorting algorithms (QuickSort,
MergeSort, InsertionSort, SelectionSort), as well as a novel "MinFinder"
algorithm by Rana et al. 2019, I conducted systematic tests across varying list
sizes. Unsorted lists were generated randomly within a fixed range to ensure a
uniform distribution of integers. The list sizes tested were 100, 200, 500,
1000, 2000, 5000, 10000, 20000, 50000, and 100000 elements. A total of 3 trials
were conducted, after which the results were averaged for each list size and
algorithm.

## MinFinder algorithm

Before diving into the results, I want to first explain the MinFinder algorithm.
The MinFinder algorithm identifies the smallest element within a list and moves
it to the first position, shifting the other elements rightward. It repeats this
process for the second-smallest element and continues until all elements are
correctly placed. This in-place, stable sorting algorithm optimizes memory usage
and maintains the original relative order of equal elements without requiring
additional space. However, the time complexity of the algorithm is $n^2$.

## Results

### Results table

The results show runtimes in nanoseconds to sort lists of different sizes:

| Size    | QuickSort  | MergeSort   | InsertionSort  | SelectionSort  | MinFinderSort |
|---------|------------|-------------|----------------|----------------|---------------|
| 100     | 835,069    | 1,035,402   | 563,972        | 896,625        | 1,049,402     |
| 200     | 603,791    | 705,222     | 1,654,027      | 2,436,250      | 2,860,764     |
| 500     | 801,764    | 1,073,778   | 4,050,097      | 3,060,902      | 4,998,180     |
| 1,000   | 1,769,236  | 2,499,694   | 4,667,875      | 16,160,569     | 5,334,375     |
| 2,000   | 2,897,875  | 4,374,486   | 6,070,389      | 46,631,208     | 6,947,583     |
| 5,000   | 3,471,180  | 6,056,486   | 24,197,541     | 61,479,097     | 31,239,292    |
| 10,000  | 4,668,388  | 10,774,819  | 71,236,194     | 100,279,250    | 100,324,416   |
| 20,000  | 8,868,902  | 37,326,638  | 419,395,347    | 489,596,388    | 425,356,583   |
| 50,000  | 21,515,000 | 197,699,666 | 2,001,218,958  | 2,182,408,583  | 2,360,330,291 |
| 100,000 | 40,652,778 | 777,435,653 | 10,241,873,778 | 11,196,644,027 | 9,915,203,333 |

### Results plot

<figure>
  <img src="https://raw.githubusercontent.com/cory0417/dsa/assignment-04/assignments/04-sorting/sort_performance.svg?sanitize=true" alt="runtime plot">
  <figcaption>Nominal values for $n\log{n}$ and $n^2$ runtime algorithms have 
been added to the plot for comparison.</figcaption>
</figure>

## Conclusions/Observations

- For **small to medium-sized lists** (up to 10,000 elements), all algorithms
  are quite similar in terms of their runtime.
- QuickSort is the **most efficient** on average in terms of the runtime, making
  it useful for very large lists. It has a runtime of $n \log{n}$
- MergeSort is the second most efficient on average.
- SelectionSort, InsertionSort, and MinFinder all follow $n^2$ performance.
