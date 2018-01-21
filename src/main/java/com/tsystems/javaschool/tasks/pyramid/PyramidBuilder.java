package com.tsystems.javaschool.tasks.pyramid;

import java.util.*;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */

    public int[][] buildPyramid(List<Integer> inputNumbers) {

        int rows = 0, cols = 0, x = 0, count_i, m_i, m_j, n_i=0 , n_j=0, index=0;
        int[][] pyramid;
        if(inputNumbers.contains(null))
            throw new CannotBuildPyramidException();
        while(rows<inputNumbers.size())
        {
            rows+=x;
            x++;
        }
        if(rows != inputNumbers.size())
        {
             pyramid = new int[rows][cols];
             throw new CannotBuildPyramidException();
        }
        else
        {
            rows = --x;
            cols = 2*rows-1;
            try{
                pyramid = new int[rows][cols];
            } catch(Exception e){
                throw new CannotBuildPyramidException();
            }
            Collections.sort(inputNumbers);
            pyramid[0][cols/2] = inputNumbers.get(index);
            index++;
            m_i = 1; m_j = cols/2-1;
            for(int i = 0; i<rows; i++)
            {
                count_i = i+1;
                for(int j = 0; j<cols; j++)
                {
                    if(i==m_i && j==m_j)
                    {
                        if(count_i == i+1)
                        {
                            n_i = i+1;
                            n_j = j-1;
                        }
                        if(count_i > 0)
                        {
                            pyramid[i][j] = inputNumbers.get(index);
                            index++;
                            m_j += 2;
                            count_i--;
                        }
                        if(count_i == 0)
                        {
                            m_i = n_i;
                            m_j = n_j;
                        }
                    }
                    else if (pyramid[i][j] == 0)
                    {
                        pyramid[i][j]=0;
                    }
                }
            }
        }
        return pyramid;
    }
}
//test
