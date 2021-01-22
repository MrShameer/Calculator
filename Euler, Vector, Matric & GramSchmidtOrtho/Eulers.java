import java.io.IOException;
import java.lang.Math;

class Eulers {
    double[] h, x, y;

    // Consider a differential equation
    // dy/dx=(x + y + xy)
    double func(double x, double y) {
        return (x + y + x * y);
    }

    // Function for Euler formula
    String euler(double x0, double y, double h, double x) {
        double temp = -0;

        // Iterating till the point at which we
        // need approximation
        while (x0 < x) {
            temp = y;
            y = y + h * func(x0, y);
            x0 = x0 + h;
        }
        // Printing approximation
        return "Approximate solution at x = "
                + x + " is " + y;
    }
}

/* pseudo
% Euler's Method
% Initial conditions and setup
h = (enter your step size here);  % step size
x = (enter the starting value of x here):h:(enter the ending value of x here);  % the range of x
y = zeros(size(x));  % allocate the result y
y(1) = (enter the starting value of y here);  % the initial y value
n = numel(y);  % the number of y values
% The loop to solve the DE
for i=1:n-1
    f = the expression for y' in your DE
    y(i+1) = y(i) + h * f;
end*/
