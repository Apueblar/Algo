import json
import heapq
import time

# Initialize an empty heap
heap = []
colours = ["red", "blue", "green", "yellow", "orange", "purple", "cyan", "magenta", "lime"]

# Function to add a node with its connection value to the heap
def add_node(node, value):
    # To simulate a max-heap, we push a tuple with the inverted value
    heapq.heappush(heap, (-value, node))

# Function to pop the node with the highest value from the heap
def pop_node():
    if heap:
        value, node = heapq.heappop(heap)
        return node, -value  # Return the original value
    return None, None

# Function to peek at the highest value node without removing it
def peek():
    if heap:
        value, node = heap[0]
        return node, -value
    return None, None

from helper import draw_coloured_map, generate_graph_map

def greedy(mapa):
    for node in mapa.keys():
        add_node(node, len(mapa[node]))  # Add node and its connection count

    node_colours = {}

    while peek() != (None,None): # 100 iterations
        node, v = pop_node()
        conections = mapa[node]
        neighbour_colors = set()

        for conection in conections:
            if str(conection) in node_colours:
                colour = node_colours[str(conection)]
                neighbour_colors.add(colour)

        for possible_node_colour in colours:
            if possible_node_colour not in neighbour_colors:
                node_colours[node] = possible_node_colour
                break

    return node_colours

if __name__ == "__main__":
    repetitions = 100
    for n in range(3,17):
        with open('sols/g' + str(2**n) + '.json') as f:
            map = json.load(f)
            f.close()
        
        start_time = time.time()  # Start time

        for _ in range(0,repetitions):
            solution = greedy(map["graph"])
        
        end_time = time.time()  # End time
        elapsed_time = end_time - start_time
        print(f"{n} - Elapsed time: {elapsed_time/repetitions} seconds")

        input("Continue")

        # if solution:
        #     print("Solution found:", solution)
        #     draw_coloured_map(map, solution)
        #     with open('solution.json', 'w') as f:
        #         json.dump(solution, f)
        #         f.close()
        # else:
        #     print("Solution not found.")
    
