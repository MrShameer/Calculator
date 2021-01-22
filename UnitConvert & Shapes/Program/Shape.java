class Shape{
	double round = 100.0;
	double cir=0.0,area=0.0,dia=0.0;
	
	void round(double r){
		cir=Math.round(round*2*Math.PI*r)/round;
		area=Math.round(round*Math.PI*r*r)/round;
		dia=r*2;
	}

	void rec(double x, double y){
		cir=2*x+2*y;
		area=x*y;
	}

	void poly(double s, double a){
		cir=s*a;
		area=Math.round(round*(1.0/4.0*a*s*s)/(Math.tan(Math.PI/a)))/round;
	}

}