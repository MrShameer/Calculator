import java.lang.Math;
class Matric{
	double[] tr;
	double[] tr2;
	String add(double[] x, double[] y){
		if(x.length==4){
			return (x[0]+y[0])+" , "+(x[1]+y[1])+"\n"+
					(x[2]+y[2])+" , "+(x[3]+y[3]);
		}
		return (x[0]+y[0])+" , "+(x[1]+y[1])+" , "+(x[2]+y[2])+"\n"+
				(x[3]+y[3])+" , "+(x[4]+y[4])+" , "+(x[5]+y[5])+"\n"+
				(x[6]+y[6])+" , "+(x[7]+y[7])+" , "+(x[8]+y[8]);
	}

	String sub(double[] x, double[] y){
		if(x.length==4){
			return (x[0]-y[0])+" , "+(x[1]-y[1])+"\n"+
					(x[2]-y[2])+" , "+(x[3]-y[3]);
		}
		return (x[0]-y[0])+" , "+(x[1]-y[1])+" , "+(x[2]-y[2])+"\n"+
				(x[3]-y[3])+" , "+(x[4]-y[4])+" , "+(x[5]-y[5])+"\n"+
				(x[6]-y[6])+" , "+(x[7]-y[7])+" , "+(x[8]-y[8]);
	}

	String mul(double[] x, double[] y){
		if(x.length==4){
			return (x[0]*y[0]+x[1]*y[2])+" , "+(x[0]*y[1]+x[1]*y[3])+"\n"+
					(x[2]*y[0]+x[3]*y[2])+" , "+(x[2]*y[1]+x[3]*y[3]);
		}
		return (x[0]*y[0]+x[1]*y[3]+x[2]*y[6])+" , "+(x[0]*y[1]+x[1]*y[4]+x[2]*y[7])+" , "+(x[0]*y[2]+x[1]*y[5]+x[2]*y[8])+"\n"+
				(x[3]*y[0]+x[4]*y[3]+x[5]*y[6])+" , "+(x[3]*y[1]+x[4]*y[4]+x[5]*y[7])+" , "+(x[3]*y[2]+x[4]*y[5]+x[5]*y[8])+"\n"+
				(x[6]*y[0]+x[7]*y[3]+x[8]*y[6])+" , "+(x[6]*y[1]+x[7]*y[4]+x[8]*y[7])+" , "+(x[6]*y[2]+x[7]*y[5]+x[8]*y[8]);
	}

	double det(double[] x){
		if(x.length==4){
			return x[0]*x[3]-x[1]*x[2];
		}
		return x[0]*(x[4]*x[8]-x[5]*x[7])-x[1]*(x[3]*x[8]-x[5]*x[6])+x[2]*(x[3]*x[7]-x[4]*x[6]);
	}

	String inv(double[] x){
		//double z=0;
		if(x.length==4){
			double d=1/det(x);
			trans(x);
			return d*tr2[0]+" , "+d*tr2[1]+"\n"+
					d*tr2[2]+" , "+d*tr2[3];
		}
		double d=1/det(x);
		trans(x);
		return d*(tr[4]*tr[8]-tr[5]*tr[7])+" , "+d*-(tr[3]*tr[8]-tr[5]*tr[6])+" , "+d*(tr[3]*tr[7]-tr[4]*tr[6])+"\n"+
				d*-(tr[1]*tr[8]-tr[2]*tr[7])+" , "+d*(tr[0]*tr[8]-tr[2]*tr[6])+" , "+d*-(tr[0]*tr[7]-tr[1]*tr[6])+"\n"+
				d*(tr[1]*tr[5]-tr[2]*tr[4])+" , "+d*-(tr[0]*tr[5]-tr[2]*tr[3])+" , "+d*(tr[0]*tr[4]-tr[1]*tr[3]);

	}

	String trans(double[] x){
		if(x.length==4){
			double z=0.0;
			z=x[0];
			x[0]=x[3];
			x[3]=z;
			x[1]=-x[1];
			x[2]=-x[2];
			tr2=x;

			return x[0]+" , "+x[1]+"\n"+
					x[2]+" , "+x[3];
		}
		double z=0.0;
		z=x[1];
		x[1]=x[3];
		x[3]=z;

		z=x[2];
		x[2]=x[6];
		x[6]=z;

		z=x[5];
		x[5]=x[7];
		x[7]=z;

		tr=x;

		return x[0]+" , "+x[1]+" , "+x[2]+"\n"+
				x[3]+" , "+x[4]+" , "+x[5]+"\n"+
				x[6]+" , "+x[7]+" , "+x[8];

	}
	//"Addition","Subtraction","Multiplication","Determinants","Inverse Matrix","Transpose Matric","Scalar Triple Product"
}