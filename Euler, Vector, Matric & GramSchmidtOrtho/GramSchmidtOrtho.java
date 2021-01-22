//import java.util.ArrayList;
import java.lang.Math;

class GramSchmidtOrtho {
    double round=100.0;
    /*double v1=0, v2=0;

    double v1(int op, double[] p){
        if(p.length==2){ // 2x2
        return (p[0]+p[1]);
        } // 3x3
        return (p[0]+p[1]+p[2]);
    }

    double v2(int op, double[] q){
        if(q.length==2){ // 2x2
            return Math.sqrt((q[0]+q[1])-((((Math.sqrt(q[0]+q[1])) * v1)/(v1 * v1))*(v1)));
        } // 3x3
        return Math.sqrt((q[0]+q[1]+q[2])-((((Math.sqrt(q[0]+q[1]+q[2])) * v1)/(v1 * v1))*(v1)));
    }

    double v3(int op, double[] r){
        if(r.length==2){ // 2x2
            return Math.sqrt((r[0]+r[1])-((((Math.sqrt(r[0]+r[1])) * v1)/(v1 *v1))*(v1))-((((Math.sqrt(r[0]+r[1])) * v2)/(v2 * v2))*(v2)));
        } // 3x3
        return Math.sqrt((r[0]+r[1]+r[2])-((((Math.sqrt(r[0]+r[1]+r[2])) * v1)/(v1 * v1))*(v1))-((((Math.sqrt(r[0]+r[1]+r[2])) * v2)/(v2 * v2))*(v2)));
    }*/




    String v1(double[]a){
        if(a.length==2)
            return a[0]+" + "+a[1];
        else if(a.length==3)
            return a[0]+" + "+a[1]+" + "+a[2];

        return "";
    }

    String v2(double[] a, double[] b){
        if(b.length==2)
            return Math.round(round*(b[0]-((a[0]*b[0]+a[1]*b[1])/(a[0]*a[0]+a[1]*a[1])*a[0])))/round+" + "+Math.round(round*(b[1]-((a[0]*b[0]+a[1]*b[1])/(a[0]*a[0]+a[1]*a[1])*a[1])))/round;
        else if(b.length==3)
            return Math.round(round*(b[0]-((a[0]*b[0]+a[1]*b[1]+a[2]*b[2])/(a[0]*a[0]+a[1]*a[1]+a[2]*a[2])*a[0])))/round+" + "+Math.round(round*(b[1]-((a[0]*b[0]+a[1]*b[1]+a[2]*b[2])/(a[0]*a[0]+a[1]*a[1]+a[2]*a[2])*a[1])))/round+" + "+Math.round(round*(b[2]-((a[0]*b[0]+a[1]*b[1]+a[2]*b[2])/(a[0]*a[0]+a[1]*a[1]+a[2]*a[2])*a[2])));

        return "";
    }

    String v3(double[] a, double[] b, double[] c){
        if(c.length==2)
            return Math.round(round*(c[0]-((a[0]*c[0]+a[1]*c[1])/(a[0]*a[0]+a[1]*a[1])*a[0])-((b[0]*c[0]+b[1]*c[1])/(b[0]*b[0]+b[1]*b[1])*b[0])))/round+" + "+Math.round(round*(c[1]-((a[0]*c[0]+a[1]*c[1])/(a[0]*a[0]+a[1]*a[1])*a[1])-((b[0]*c[0]+b[1]*c[1])/(b[0]*b[0]+b[1]*b[1])*b[1])));
        else if(c.length==3)
            return Math.round(round*(c[0]-((a[0]*c[0]+a[1]*c[1]+a[2]*c[2])/(a[0]*a[0]+a[1]*a[1]+a[2]*a[2])*a[0])-((b[0]*c[0]+b[1]*c[1]+b[2]*c[2])/(b[0]*b[0]+b[1]*b[1]+b[2])*b[0])))/round+" + "+Math.round(round*(c[1]-((a[0]*c[0]+a[1]*c[1]+a[2]*c[2])/(a[0]*a[0]+a[1]*a[1]+a[2]*a[2])*a[1])-((b[0]*c[0]+b[1]*c[1]+b[2]*c[2])/(b[0]*b[0]+b[1]*b[1]+b[2])*b[1])))/round+" + "+Math.round(round*(c[2]-((a[0]*c[0]+a[1]*c[1]+a[2]*c[2])/(a[0]*a[0]+a[1]*a[1]+a[2]*a[2])*a[2])-((b[0]*c[0]+b[1]*c[1]+b[2]*c[2])/(b[0]*b[0]+b[1]*b[1]+b[2])*b[2])))/round;
//c[0]+" + "+c[1]+" + "+c[2]+" - [("++")/("++")] x "+v1(a)+" - [("++")/("++")] x "+v2(a,b);

        return "";
    }

    String ans(double[] a, double[] b, double[] c){
        if(a.length==2){
            if(b==null)
                return "V1 = "+v1(a);
            else if(b.length==2){
                if(c==null){
                    return "V1 = "+v1(a)+"\nV2 = "+v2(a,b);
                }
                else if(c.length==2)
                    return "V1 = "+v1(a)+"\nV2 = "+v2(a,b)+"\nV3 = "+v3(a,b,c);
            }
            
        }
        else if(a.length==3){
            if(b==null)
                return "V1 = "+v1(a);
            else if(b.length==3){
                if(c==null){
                    return "V1 = "+v1(a)+"\nV2 = "+v2(a,b);
                }
                else if(c.length==3)
                    return "V1 = "+v1(a)+"\nV2 = "+v2(a,b)+"\nV3 = "+v3(a,b,c);
            }
        }

        return "";
    }
}

    /*
    Uk = (Vk - (Uk-1*Vk)*Uk-1 - ... - (U1*Vk)*Uk1)/||Uk||
    Where ||Uk|| = Length of Uk

    Vectors U own to finalArray and the V ones own to array
    */
    /*public ArrayList< double[] > gramSchmidt(ArrayList< double[] > array)
    {
        ArrayList< double[] > finalArray= new ArrayList<>();

        //I set the first vector because it never changes, it's always the first vector of the array receive divided between it's length
        finalArray.add(multiplyScalarPerVector(1/(calculateVectorLength(array.get(0))), array.get(0)));
        //This last line is the one that modifies EVERYTHING in array and it shouldn't

        for(int i=1; i<array .size(); i++)
        {
            double[] newVector= substractVectors(array .get(i), projection(finalArray.get(i-1),array .get(i)));
            for(int e=i-1;e>0;e--)
            {
                newVector= substractVectors(newVector, projection(finalArray.get(e-1),array .get(i)));
            }
            newVector= multiplyScalarPerVector(1/(calculateVectorLength(newVector)), newVector);
            finalArray.add(newVector);
        }
        return finalArray;
    }

    //Obtain the (Uk-1*Vk)*Uk-1
    public double[] projection(double[] array1, double[] array2)
    {
        double dotProductResult= dotProduct(array1,array2);
        double[] finalVector= multiplyScalarPerVector(dotProductResult, array1);
        return finalVector;
    }

    //To do Uk-1*Vk
    public double dotProduct(double[] vector1, double[] vector2)
    {
        double result = 0;
        for(int i=0; i<vector1.length; i++)
        {
            result +=vector1[i]*vector2[i];
        }
        return result ;
    }

    public double[] multiplyScalarPerVector(double scalar, double[] vector)
    {
        double[] newVector = new double[vector.length];
        for(int i=0; i<vector.length; i++)
        {
            newVector[i] = scalar*vector[i];
        }
        return newVector;
    }

    public double[] substractVectors(double[] vector1, double[] vector2)
    {
        double[] finalVector= new double[vector1.length];
        for(int i=0; i<vector1.length; i++)
        {
            finalVector[i] = vector1[i] - vector2[i];
        }
        return finalVector;
    }

    //Calculate the euclidean distance
    public double calculateVectorLength(double[] vector)
    {
        double result = 0;
        for(int i=0; i<vector.length; i++)
        {
            result +=Math.pow(vector[i], 2);
        }
        return Math.sqrt(result );
    }
}*/
