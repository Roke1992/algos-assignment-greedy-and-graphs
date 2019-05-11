/**
 * Public Transit
 * Author: Your Name and Carolyn Yao
 * Does this compile? Y/N
 */
package csci323;
public class FastestRoutePublicTransit {
	 /**
	   * The algorithm that could solve for shortest travel time from a station S
	   * to a station T given various tables of information about each edge (u,v)
	   *
	   * @param S the s th vertex/station in the transit map, start From
	   * @param T the t th vertex/station in the transit map, end at
	   * @param startTime the start time in terms of number of minutes from 5:30am
	   * @param lengths lengths[u][v] The time it takes for a train to get between two adjacent stations u and v
	   * @param first first[u][v] The time of the first train that stops at u on its way to v, int in minutes from 5:30am
	   * @param freq freq[u][v] How frequently is the train that stops at u on its way to v
	   * @return shortest travel time between S and T
	   */
		public int result_arrival[];
	  public int myShortestTravelTime(
	    int S,
	    int T,
	    int startTime,
	    int[][] lengths,
	    int[][] first,
	    int[][] freq
	  ) {
		
		  this.result_arrival[S] = startTime;
		  for(int i = 0; i < 9; i++)
		  {
			  if(lengths[S][i] != 0)    //is directly reachable from S to i
			  {
				  int arrival = startTime;
				  if(startTime > first[S][i])
					  arrival = ((startTime - first[S][i]) / freq[S][i] + 1) * freq[S][i] + first[S][i] + lengths[S][i];  //Compute the fastest arrival time from S to i

				  if(arrival < result_arrival[i])				//if it is the better solution than others before
				  {
					  result_arrival[i] = arrival;				//upgrade fastest arrival time
					  this.myShortestTravelTime(i, T, arrival, lengths, first, freq);		//recursive work to compute fastest arrival time to all other stations 
				  }
			  }
			  
		  }
	    return result_arrival[T] - result_arrival[S];	//return shortest travel time from S to T
	  }
	

	  /**
	   * Finds the vertex with the minimum time from the source that has not been
	   * processed yet.
	   * @param times The shortest times from the source
	   * @param processed boolean array tells you which vertices have been fully processed
	   * @return the index of the vertex that is next vertex to process
	   */
	  public int findNextToProcess(int[] times, Boolean[] processed) {
	    int min = Integer.MAX_VALUE;
	    int minIndex = -1;
	

	    for (int i = 0; i < times.length; i++) {
	      if (processed[i] == false && times[i] <= min) {
	        min = times[i];
	        minIndex = i;
	      }
	    }
	    return minIndex;
	  }
	

	  public void printShortestTimes(int times[]) {
	    System.out.println("Vertex Distances (time) from Source");
	    for (int i = 0; i < times.length; i++)
	        System.out.println(i + ": " + times[i] + " minutes");
	  }
	

	  /**
	   * Given an adjacency matrix of a graph, implements
	   * @param graph The connected, directed graph in an adjacency matrix where
	   *              if graph[i][j] != 0 there is an edge with the weight graph[i][j]
	   * @param source The starting vertex
	   */
	  public void shortestTime(int graph[][], int source) {
	    int numVertices = graph[0].length;
	

	    // This is the array where we'll store all the final shortest times
	    int[] times = new int[numVertices];
	

	    // processed[i] will true if vertex i's shortest time is already finalized
	    Boolean[] processed = new Boolean[numVertices];
	

	    // Initialize all distances as INFINITE and processed[] as false
	    for (int v = 0; v < numVertices; v++) {
	      times[v] = Integer.MAX_VALUE;
	      processed[v] = false;
	    }
	

	    // Distance of source vertex from itself is always 0
	    times[source] = 0;
	

	    // Find shortest path to all the vertices
	    for (int count = 0; count < numVertices - 1 ; count++) {
	      // Pick the minimum distance vertex from the set of vertices not yet processed.
	      // u is always equal to source in first iteration.
	      // Mark u as processed.
	      int u = findNextToProcess(times, processed);
	      processed[u] = true;
	

	      // Update time value of all the adjacent vertices of the picked vertex.
	      for (int v = 0; v < numVertices; v++) {
	        // Update time[v] only if is not processed yet, there is an edge from u to v,
	        // and total weight of path from source to v through u is smaller than current value of time[v]
	        if (!processed[v] && graph[u][v]!=0 && times[u] != Integer.MAX_VALUE && times[u]+graph[u][v] < times[v]) {
	          times[v] = times[u] + graph[u][v];
	        }
	      }
	    }
	

	    printShortestTimes(times);
	  }
	

	  public static void main (String[] args) {
	    /* length(e) */
	    int lengthTimeGraph[][] = new int[][]{
	      {0, 4, 0, 0, 0, 0, 0, 8, 0},
	      {4, 0, 8, 0, 0, 0, 0, 11, 0},
	      {0, 8, 0, 7, 0, 4, 0, 0, 2},
	      {0, 0, 7, 0, 9, 14, 0, 0, 0},
	      {0, 0, 0, 9, 0, 10, 0, 0, 0},
	      {0, 0, 4, 14, 10, 0, 2, 0, 0},
	      {0, 0, 0, 0, 0, 2, 0, 1, 6},
	      {8, 11, 0, 0, 0, 0, 1, 0, 7},
	      {0, 0, 2, 0, 0, 0, 6, 7, 0}
	    };
	    int FirstTrainGraph[][] = new int[][]{
	      {0, 13, 0, 0, 0, 0, 0, 17, 0},
	      {19, 0, 12, 0, 0, 0, 0, 5, 0},
	      {0, 30, 0, 21, 0, 6, 0, 0, 23},
	      {0, 0, 3, 0, 12, 14, 0, 0, 0},
	      {0, 0, 0, 9, 0, 10, 0, 0, 0},
	      {0, 0, 24, 14, 5, 0, 12, 0, 0},
	      {0, 0, 0, 0, 0, 12, 0, 21, 6},
	      {18, 11, 0, 0, 0, 0, 21, 0, 17},
	      {0, 0, 12, 0, 0, 0, 16, 17, 0}
	    };
	    int TrainFreqGraph[][] = new int[][]{
	      {0, 18, 0, 0, 0, 0, 0, 23, 0},
	      {35, 0, 24, 0, 0, 0, 0, 31, 0},
	      {0, 42, 0, 32, 0, 24, 0, 0, 18},
	      {0, 0, 34, 0, 19, 24, 0, 0, 0},
	      {0, 0, 0, 31, 0, 20, 0, 0, 0},
	      {0, 0, 24, 24, 30, 0, 22, 0, 0},
	      {0, 0, 0, 0, 0, 23, 0, 18, 27},
	      {24, 26, 0, 0, 0, 0, 31, 0, 17},
	      {0, 0, 20, 0, 0, 0, 16, 27, 0}
	    };
	    FastestRoutePublicTransit t = new FastestRoutePublicTransit();
//	    t.shortestTime(lengthTimeGraph, 0);
	    
	    t.result_arrival = new int[9];
	    for(int i = 0; i < 9; i++)
	    	t.result_arrival[i] = Integer.MAX_VALUE;
	    
	    System.out.println(t.myShortestTravelTime(0, 8, 50, lengthTimeGraph, FirstTrainGraph, TrainFreqGraph));

	    // You can create a test case for your implemented method for extra credit below
	  }
	}



package csci323;
public class FastestRoutePublicTransit {
	 /**
	   * The algorithm that could solve for shortest travel time from a station S
	   * to a station T given various tables of information about each edge (u,v)
	   *
	   * @param S the s th vertex/station in the transit map, start From
	   * @param T the t th vertex/station in the transit map, end at
	   * @param startTime the start time in terms of number of minutes from 5:30am
	   * @param lengths lengths[u][v] The time it takes for a train to get between two adjacent stations u and v
	   * @param first first[u][v] The time of the first train that stops at u on its way to v, int in minutes from 5:30am
	   * @param freq freq[u][v] How frequently is the train that stops at u on its way to v
	   * @return shortest travel time between S and T
	   */
		public int result_arrival[];
	  public int myShortestTravelTime(
	    int S,
	    int T,
	    int startTime,
	    int[][] lengths,
	    int[][] first,
	    int[][] freq
	  ) {
		
		  this.result_arrival[S] = startTime;
		  for(int i = 0; i < 9; i++)
		  {
			  if(lengths[S][i] != 0)    //is directly reachable from S to i
			  {
				  int arrival = startTime;
				  if(startTime > first[S][i])
					  arrival = ((startTime - first[S][i]) / freq[S][i] + 1) * freq[S][i] + first[S][i] + lengths[S][i];  //Compute the fastest arrival time from S to i

				  if(arrival < result_arrival[i])				//if it is the better solution than others before
				  {
					  result_arrival[i] = arrival;				//upgrade fastest arrival time
					  this.myShortestTravelTime(i, T, arrival, lengths, first, freq);		//recursive work to compute fastest arrival time to all other stations 
				  }
			  }
			  
		  }
	    return result_arrival[T] - result_arrival[S];	//return shortest travel time from S to T
	  }
	

	  /**
	   * Finds the vertex with the minimum time from the source that has not been
	   * processed yet.
	   * @param times The shortest times from the source
	   * @param processed boolean array tells you which vertices have been fully processed
	   * @return the index of the vertex that is next vertex to process
	   */
	  public int findNextToProcess(int[] times, Boolean[] processed) {
	    int min = Integer.MAX_VALUE;
	    int minIndex = -1;
	

	    for (int i = 0; i < times.length; i++) {
	      if (processed[i] == false && times[i] <= min) {
	        min = times[i];
	        minIndex = i;
	      }
	    }
	    return minIndex;
	  }
	

	  public void printShortestTimes(int times[]) {
	    System.out.println("Vertex Distances (time) from Source");
	    for (int i = 0; i < times.length; i++)
	        System.out.println(i + ": " + times[i] + " minutes");
	  }
	

	  /**
	   * Given an adjacency matrix of a graph, implements
	   * @param graph The connected, directed graph in an adjacency matrix where
	   *              if graph[i][j] != 0 there is an edge with the weight graph[i][j]
	   * @param source The starting vertex
	   */
	  public void shortestTime(int graph[][], int source) {
	    int numVertices = graph[0].length;
	

	    // This is the array where we'll store all the final shortest times
	    int[] times = new int[numVertices];
	

	    // processed[i] will true if vertex i's shortest time is already finalized
	    Boolean[] processed = new Boolean[numVertices];
	

	    // Initialize all distances as INFINITE and processed[] as false
	    for (int v = 0; v < numVertices; v++) {
	      times[v] = Integer.MAX_VALUE;
	      processed[v] = false;
	    }
	

	    // Distance of source vertex from itself is always 0
	    times[source] = 0;
	

	    // Find shortest path to all the vertices
	    for (int count = 0; count < numVertices - 1 ; count++) {
	      // Pick the minimum distance vertex from the set of vertices not yet processed.
	      // u is always equal to source in first iteration.
	      // Mark u as processed.
	      int u = findNextToProcess(times, processed);
	      processed[u] = true;
	

	      // Update time value of all the adjacent vertices of the picked vertex.
	      for (int v = 0; v < numVertices; v++) {
	        // Update time[v] only if is not processed yet, there is an edge from u to v,
	        // and total weight of path from source to v through u is smaller than current value of time[v]
	        if (!processed[v] && graph[u][v]!=0 && times[u] != Integer.MAX_VALUE && times[u]+graph[u][v] < times[v]) {
	          times[v] = times[u] + graph[u][v];
	        }
	      }
	    }
	

	    printShortestTimes(times);
	  }
	

	  public static void main (String[] args) {
	    /* length(e) */
	    int lengthTimeGraph[][] = new int[][]{
	      {0, 4, 0, 0, 0, 0, 0, 8, 0},
	      {4, 0, 8, 0, 0, 0, 0, 11, 0},
	      {0, 8, 0, 7, 0, 4, 0, 0, 2},
	      {0, 0, 7, 0, 9, 14, 0, 0, 0},
	      {0, 0, 0, 9, 0, 10, 0, 0, 0},
	      {0, 0, 4, 14, 10, 0, 2, 0, 0},
	      {0, 0, 0, 0, 0, 2, 0, 1, 6},
	      {8, 11, 0, 0, 0, 0, 1, 0, 7},
	      {0, 0, 2, 0, 0, 0, 6, 7, 0}
	    };
	    int FirstTrainGraph[][] = new int[][]{
	      {0, 13, 0, 0, 0, 0, 0, 17, 0},
	      {19, 0, 12, 0, 0, 0, 0, 5, 0},
	      {0, 30, 0, 21, 0, 6, 0, 0, 23},
	      {0, 0, 3, 0, 12, 14, 0, 0, 0},
	      {0, 0, 0, 9, 0, 10, 0, 0, 0},
	      {0, 0, 24, 14, 5, 0, 12, 0, 0},
	      {0, 0, 0, 0, 0, 12, 0, 21, 6},
	      {18, 11, 0, 0, 0, 0, 21, 0, 17},
	      {0, 0, 12, 0, 0, 0, 16, 17, 0}
	    };
	    int TrainFreqGraph[][] = new int[][]{
	      {0, 18, 0, 0, 0, 0, 0, 23, 0},
	      {35, 0, 24, 0, 0, 0, 0, 31, 0},
	      {0, 42, 0, 32, 0, 24, 0, 0, 18},
	      {0, 0, 34, 0, 19, 24, 0, 0, 0},
	      {0, 0, 0, 31, 0, 20, 0, 0, 0},
	      {0, 0, 24, 24, 30, 0, 22, 0, 0},
	      {0, 0, 0, 0, 0, 23, 0, 18, 27},
	      {24, 26, 0, 0, 0, 0, 31, 0, 17},
	      {0, 0, 20, 0, 0, 0, 16, 27, 0}
	    };
	    FastestRoutePublicTransit t = new FastestRoutePublicTransit();
//	    t.shortestTime(lengthTimeGraph, 0);
	    
	    t.result_arrival = new int[9];
	    for(int i = 0; i < 9; i++)
	    	t.result_arrival[i] = Integer.MAX_VALUE;
	    
	    System.out.println(t.myShortestTravelTime(0, 8, 50, lengthTimeGraph, FirstTrainGraph, TrainFreqGraph));

	    // You can create a test case for your implemented method for extra credit below
	  }
	}


