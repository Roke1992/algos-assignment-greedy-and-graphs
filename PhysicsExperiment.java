//Author  Md  hassan
package csci323;
public class Physical {

  public int[][] scheduleExperiments(
    int numStudents,
    int numSteps,
    int[][] signUpTable
  ) {
    // Your scheduleTable is initialized as all 0's so far. Your code will put 1's
    // in the table in the right places based on the return description
    int[][] scheduleTable = new int[numStudents + 1][numSteps + 1];
    int current_step_num = 1;
    while(current_step_num <= numSteps)
    {
    	int max_steps = 0;
	    int max_step_owner = 0;
	    for(int student = 1; student <= numStudents; student++)
	    {
	    	int steps = 0;
	    	while(signUpTable[student][current_step_num + steps] != 0)
	    	{
	    		steps ++;
	    		if(current_step_num + steps > numSteps)
	    			break;
	    	}
	    	if(steps > max_steps)
	    	{
	    		max_steps = steps;
	    		max_step_owner = student;
	    	}
	    }
	    for(int i = 0; i < max_steps; i++)
	    {
	    	scheduleTable[max_step_owner][current_step_num + i] = 1;
	    }
	    current_step_num += max_steps;
    }
    


    return scheduleTable;
  }


  public int[][] makeSignUpLookup(int numSteps, int[][] studentSignUps) {
    int numStudents = studentSignUps.length;
    int[][] lookupTable = new int[numStudents+1][numSteps + 1];
    for (int student = 1; student <= numStudents; student++) {
      int[] signedUpSteps = studentSignUps[student-1];
      for (int i = 0; i < signedUpSteps.length; i++) {
        lookupTable[student][signedUpSteps[i]] = 1;
      }
    }
    return lookupTable;
  }


  public void printResults(int[][] schedule) {
    for (int student = 1; student < schedule.length; student++) {
      int[] curStudentSchedule = schedule[student];
      System.out.print("Student " + student + ": ");
      for (int step = 1; step < curStudentSchedule.length; step++) {
        if (curStudentSchedule[step] == 1) {
          System.out.print(step + " ");
        }
      }
      System.out.println("");
    }
  }


  public boolean inputsValid(int numStudents, int numSteps, int signUps[][]) {
    int studentSignUps = signUps.length;


    // Check if there are any students or signups
    if (numStudents < 1 || studentSignUps < 1) {
      System.out.println("You either did not put in any student or any signups");
      return false;
    }


    // Check if the number of students and sign-up rows matches
    if (numStudents != studentSignUps) {
      System.out.println("You input " + numStudents + " students but your signup suggests " + signUps.length);
      return false;
    }


    // Check that all steps are guaranteed in the signups
    int[] stepsGuaranteed = new int[numSteps + 1];
    for (int i = 0; i < studentSignUps; i++) {
      for (int j = 0; j < signUps[i].length; j++) {
        stepsGuaranteed[signUps[i][j]] = 1;
      }
    }
    for (int step = 1; step <= numSteps; step++) {
      if (stepsGuaranteed[step] != 1) {
        System.out.println("Your signup is incomplete because not all steps are guaranteed.");
        return false;
      }
    }


    return true;
  }


  public void makeExperimentAndSchedule(int experimentNum, int numStudents, int numSteps, int[][] signUps) {
    System.out.println("----Experiment " + experimentNum + "----");
    if (!inputsValid(numStudents, numSteps, signUps)) {
      System.out.println("Experiment signup info is invalid");
      return;
    }
    int[][] signUpsLookup = makeSignUpLookup(numSteps, signUps);
    int[][] schedule = scheduleExperiments(numStudents, numSteps, signUpsLookup);
    printResults(schedule);
    System.out.println("");
  }

  
  public static void main(String args[]){
	  Physical pe = new Physical();


    // Experiment 1: Example 1 from README, 3 students, 6 steps:
    int[][] signUpsExperiment1 = {{1, 2, 3, 5}, {2, 3, 4}, {1, 4, 5, 6}};
    pe.makeExperimentAndSchedule(1, 3, 6, signUpsExperiment1);


    // Experiment 2: Example 2 from README, 4 students, 8 steps
    int[][] signUpsExperiment2 = {{5, 7, 8}, {2, 3, 4, 5, 6}, {1, 5, 7, 8}, {1, 3, 4, 8}};
    pe.makeExperimentAndSchedule(2, 4, 8, signUpsExperiment2);


    // Experiment 3: Another test case, 5 students, 11 steps
    int[][] signUpsExperiment3 = {{7, 10, 11}, {8, 9, 10}, {2, 3, 4, 5, 7}, {1, 5, 6, 7, 8}, {1, 3, 4, 8}};
    pe.makeExperimentAndSchedule(3, 5, 11, signUpsExperiment3);
  }
}
