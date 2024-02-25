import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

# Load the data
data = pd.read_csv('sort_results.csv')

# Plotting sorting algorithms
plt.figure(figsize=(10, 6))
plt.plot(data['Size'], data['QuickSort'], label='QuickSort', marker='o')
plt.plot(data['Size'], data['MergeSort'], label='MergeSort', marker='o')
plt.plot(data['Size'], data['InsertionSort'], label='InsertionSort', marker='o')
plt.plot(data['Size'], data['SelectionSort'], label='SelectionSort', marker='o')
plt.plot(data['Size'], data['MinFinderSort'], label='MinFinderSort', marker='o')

# Plotting nlogn and n^2 for comparison
sizes = data['Size']
nlogn = [size * np.log(size) for size in sizes]
nsquared = [size ** 2 for size in sizes]


plt.plot(sizes, nlogn, label='$n \log n$ (normalized)',
         linestyle='--', color='purple')
plt.plot(sizes, nsquared, label='$n^2$ (normalized)',
         linestyle='--', color='pink')

plt.title('Sorting Algorithm Performance with $n \log n$ and $n^2$ Comparison')
plt.xlabel('List Size')
plt.ylabel('Runtime')
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig('sort_performance.png')
plt.show()

