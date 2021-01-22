import java.lang.Math;
class Vector{
	double meg(int op,double[] r){
		if(r.length==2){
			return Math.sqrt(r[0]*r[0]+r[1]*r[1]);
		}
		return Math.sqrt(r[0]*r[0]+r[1]*r[1]+r[2]*r[2]);
	}

	double rec(int op,double[] x, double[] y){
		if(op==2){
			if(x.length==2&&y.length==2)
				return Math.sqrt(Math.pow(x[0]-y[0],2)+Math.pow(x[1]-y[1],2));
			return Math.sqrt(Math.pow(x[0]-y[0],2)+Math.pow(x[1]-y[1],2)+Math.pow(x[2]-y[2],2));
		}
		else if(op==5){
			if(x.length==2&&y.length==2)
				return x[0]*y[0]+x[1]*y[1];
			return x[0]*y[0]+x[1]*y[1]+x[2]*y[2];
		}
		return 0.0;
	}

	String add(double[] x, double[] y){
		if(x.length==2&&y.length==2)
			return (x[0]+y[0])+","+(x[1]+y[1]);
		return (x[0]+y[0])+","+(x[1]+y[1])+","+(x[2]+y[2]);
	}

	double scalar(double k, double[] x){
		if(x.length==2)
			return k*x[0]+k*x[1];
		return k*x[0]+k*x[1]+k*x[2];
	}
}