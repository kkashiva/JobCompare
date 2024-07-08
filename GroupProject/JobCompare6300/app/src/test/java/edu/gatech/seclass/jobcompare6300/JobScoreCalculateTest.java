package edu.gatech.seclass.jobcompare6300;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JobScoreCalculateTest {
    @Test
    public void test1() {
        // Job object with sample input values, uses overloaded constructor from Job to avoid database calls
        Job job = new Job(1, 150000, 50000, 5000, 15, 3, 200);

        // Default input weights
        List<Integer> adjustedParameter = Arrays.asList(1, 1, 1, 1, 1);

        // Expected result
        double expectedScore = ((1.0 / 5.0) * job.getAYS()) + ((1.0 / 5.0) * job.getAYB()) + ((1.0 / 5.0) * job.getTrainingDevelopmentFund()) +
                ((1.0 / 5.0) * job.getLeaveTime() * (job.getAYS() / 260)) - ((1.0 / 5.0) * ((job.getAYS()/260))/8 * (260 - 52 * job.getTeleworkDaysPerWeek()));

        // Call the calculateJobScore() method to return the actual score
        double actualScore = job.calculateJobScore(adjustedParameter, job.getAYS(), job.getAYB(), job.getTrainingDevelopmentFund(), job.getLeaveTime(), job.getTeleworkDaysPerWeek());

        // print score
        System.out.println(expectedScore);
        System.out.println(actualScore);

        // Test if actual score matches the expected score
        assertEquals(expectedScore, actualScore, 0.001);
    }

    @Test
    public void test2() {
        // Job object with sample input values, uses overloaded constructor from Job to avoid database calls
        Job job = new Job(1, 150000, 50000, 5000, 15, 3, 200);

        // Sample input weights 2 for the yearly salary, 2 for the yearly bonus, 2 for the Training and Development Fund, and 1 for all other factors
        List<Integer> adjustedParameter = Arrays.asList(2, 2, 2, 1, 1);

        // Expected result
        double expectedScore = ((2.0 / 8.0) * job.getAYS()) + ((2.0 / 8.0) * job.getAYB()) + ((2.0 / 8.0) * job.getTrainingDevelopmentFund()) +
                ((1.0 / 8.0) * job.getLeaveTime() * (job.getAYS() / 260)) - ((1.0 / 8.0) * ((job.getAYS()/260))/8 * (260 - 52 * job.getTeleworkDaysPerWeek()));

        // Call the calculateJobScore() method to return the actual score
        double actualScore = job.calculateJobScore(adjustedParameter, job.getAYS(), job.getAYB(), job.getTrainingDevelopmentFund(), job.getLeaveTime(), job.getTeleworkDaysPerWeek());

        System.out.println(expectedScore);
        System.out.println(actualScore);

        // Test if actual score matches the expected score
        assertEquals(expectedScore, actualScore, 0.001);
    }
}
