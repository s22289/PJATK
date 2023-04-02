//s22289
//Furkan Taha Unal s22289

package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main
{
    public static void Task7(String[] args)
    {
        double[][] arr = new double[40][40];
        double[] temperatures = {200,150,100,50};

        assignTemperatures(arr,temperatures);

        ArrayList<Double> tempList = new ArrayList<>();
        for (int i = arr.length - 2; i > 0; i--)
        {
            for (int j = 1; j < arr[i].length - 1; j++)
            {
                double t = arr[i - 1][j] - 4 * arr[i][j] + arr[i + 1][j] + arr[i][j - 1] + arr[i][j + 1];
                tempList.add(0 - t);
            }
        }
        double[] b = new double[tempList.size()];
        for (int i = 0; i < tempList.size(); i++)
        {
            b[i] = tempList.get(i);
        }

        ArrayList<ArrayList<Double>> fin = new ArrayList<>();
        calculateMatrix(arr, fin);

        double[][] ei = new double[fin.size()][];
        for (int i = 0; i < fin.size(); i++)
        {
            ArrayList<Double> row = fin.get(i);
            double[] copy = new double[row.size()];
            for (int j = 0; j < row.size(); j++)
            {
                copy[j] = row.get(j);
            }
            ei[i] = copy;
        }

        double[][] eiInverted = invert(ei);
        double[] d = multiplyMatrix(b, eiInverted);
        int k = 0;
        for (int i = arr.length - 2; i > 0; i--)
        {
            for (int j = 1; j < arr[i].length - 1; j++)
            {
                arr[i][j] = d[k++];
            }
        }

        try
        {
            WriteToCSV(arr);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private static void assignTemperatures(double[][] arr, double[] temp)
    {
        for (int j = 0; j < 1; j++)
        {
            for (int i = 1; i < arr[j].length - 1; i++)
            {
                arr[j][i] = temp[0];
            }
        }

        for (int j = 1; j < arr.length - 1; j++)
        {
            for (int i = 0; i < 1; i++)
            {
                arr[j][i] = temp[1];
            }
        }

        for (int j = arr.length - 1; j > arr.length - 2; j--)
        {
            for (int i = 1; i < arr[j].length - 1; i++)
            {
                arr[j][i] = temp[2];
            }
        }

        for (int j = 1; j < arr.length - 1; j++)
        {
            for (int i = arr[j].length - 1; i > arr[j].length - 2; i--)
            {
                arr[j][i] = temp[3];
            }
        }
    }

    private static void calculateMatrix(double[][] arr, ArrayList<ArrayList<Double>> fin)
    {
        for (int i = arr.length - 2; i > 0; i--)
        {
            for (int j = 1; j < arr[i].length - 1; j++)
            {
                ArrayList<Double> ar = new ArrayList<>();
                if (arr[i - 1][j] == 0) arr[i - 1][j] = 1;
                if (arr[i + 1][j] == 0) arr[i + 1][j] = 1;
                if (arr[i][j - 1] == 0) arr[i][j - 1] = 1;
                if (arr[i][j + 1] == 0) arr[i][j + 1] = 1;
                arr[i][j] = -4;
                for (int k = arr.length - 2; k > 0; k--)
                {
                    for (int s = 1; s < arr[k].length - 1; s++)
                    {
                        ar.add(arr[k][s]);
                        arr[k][s] = 0;
                    }
                }
                fin.add(ar);
            }
        }
    }

    private static double[] multiplyMatrix(double[] firstMatrix, double[][] secondMatrix)
    {
        double[] product = new double[firstMatrix.length];
        for (int i = 0; i < product.length; i++)
        {
            for (int j = 0; j < secondMatrix.length; j++)
            {
                product[i] += (firstMatrix[j] * secondMatrix[j][i]);
            }
        }
        return product;
    }

    private static double[][] invert(double a[][])
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        int inv = index.length;
        double c[] = new double[inv];

        for (int i = 0; i < inv; ++i)
            index[i] = i;

        for (int i = 0; i < inv; ++i)
        {
            double c1 = 0;
            for (int j = 0; j < inv; ++j)
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        int temp = 0;
        for (int j = 0; j < inv - 1; ++j)
        {
            double pi1 = 0;
            for (int i = j; i < inv; ++i)
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1)
                {
                    pi1 = pi0;
                    temp = i;
                }
            }

            int itmp = index[j];

            index[j] = index[temp];
            index[temp] = itmp;
            for (int i = j + 1; i < inv; ++i)
            {
                double pj = a[index[i]][j] / a[index[j]][j];
                a[index[i]][j] = pj;

                for (int l = j + 1; l < inv; ++l)
                {
                    a[index[i]][l] -= pj * a[index[j]][l];
                }
            }
        }

        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];

        for (int i = 0; i < n; ++i)
        {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j)
            {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k)
                {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }


    private static void WriteToCSV(double[][] arr) throws FileNotFoundException
    {
        String file = "result.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        PrintWriter pw = new PrintWriter(fileOutputStream);

        for (double[] anArr : arr)
        {
            StringBuilder content = new StringBuilder();
            for (double anAnArr : anArr)
            {
                content.append(String.format("%.3f",anAnArr)).append("|");
            }
            pw.println(content.toString());
        }
        pw.close();
    }
}