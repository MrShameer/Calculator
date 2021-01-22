class Convert{
	double round=100.0;
	double length []= {0.001, 0.01, 1.0, 0.0254, 0.3048, 1000, 1609.34};
						//mili, centi, meter, inch, foot, km, mile
	double volume []= {1.0, 0.001, 3.78541};
						//liter, mililiter, gallon
	double getlength(double unit, int a, int b){
		return Math.round(round*unit*length[a]/length[b])/round;
	}

	double gettemp(double unit, int a, int b){
		if(a==0)
			unit=unit*9.0/5.0;
		else if(a==1)
			unit=(unit-32);

		if(b==0)
			unit=unit*5.0/9.0;
		else if(b==1)
			unit=unit+32;

		return Math.round(round*unit)/round;
	}

	double getvol(double unit, int a, int b){
		round=100000.0;
		return Math.round(round*unit*volume[a]/volume[b])/round;
	}
}