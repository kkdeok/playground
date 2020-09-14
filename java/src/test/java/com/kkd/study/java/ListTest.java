package com.kkd.study.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
    
    @Test
    public void deepCopy() {
        List<Integer> oldList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> newList = new ArrayList<>(oldList);
        
        Assert.assertEquals(oldList, newList);
        Assert.assertNotSame(oldList, newList); 
    }
    
    @Test
    public void deepCopyAndChange() {
        List<Integer> oldList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> newList = new ArrayList<>(oldList);
        
        newList.add(6);
        
        Assert.assertNotEquals(oldList, newList);
        Assert.assertNotSame(oldList, newList);
    }
}
