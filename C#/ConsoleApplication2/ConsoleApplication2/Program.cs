using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


//secante, regula falsi, newton rhapson

namespace ConsoleApplication2
{
    class Program
    {

        static double F1(double t)
        {
            double ret = 0;
            ret += -3.141592 * t * Math.Exp(2.1667 * t);
            ret += (t * t) - t + 3 - Math.Sin(t);

            return ret;
        }
        static double F2(double t)
        {
            return t - Math.Pow(2, -t);
        }

        static double F(double t, int s)
        {
            switch (s)
            {
                case 1:
                    return F1(t);
                case 2:
                    return F2(t);
                default:
                    return F1(t);
            }

        }

        static double d(double t, int s)
        {
            double h = 0.000001;
            double der = (F(t - 2 * h, s) - 8 * F(t - h, s) + 8 * F(t + h, s) - F(t + 2 * h, s)) / (12 * h);
            return der;
        }

        static double Falsi(double aa, double bb, int s)
        {
            double a = aa;
            double b = bb;
            double c;
            double lim = 0.000000000001;

            do
            {

                c = ((F(b,s) * a - F(a,s) * b) / (F(b,s) - F(a,s)));
                if (F(c,s) * F(a,s) < 0)
                {
                    b = c;
                }
                else if (F(c,s) * F(a,s) > 0)
                {
                    a = c;
                }
                else
                {
                    break;
                }
            }
            while (Math.Abs(F(c,s)) > lim);

            Console.WriteLine("Regula Falsi Root: " + c);
            Console.ReadLine();

            return c;
        }

        static double NR(double t,int s)
        {
            double t1;
            do
            {
                t1 = t;
                t = t - F(t, s) / d(t, s);
            } while (Math.Abs(t - t1) > 0.000000000001);

            Console.WriteLine("Newton Rhapson Root: " + t);
            Console.ReadLine();

            return t;
        }

        static double Sec(double x0, double x1, int s)
        {
            double x2 = x1;
            while(true)
            {
                x2 = x1 - F(x1,s)*((x1-x0)/(F(x1,s)-F(x0,s)));
                if (Math.Abs(F(x2, s)) < 0.000000000001)
                    break;
                x0 = x1;
                x1 = x2;
            }

            Console.WriteLine("Secant Method Root: " + x2);
            Console.ReadLine();

            return x2;
        }

        static void Main(string[] args)
        {
           // Sec(0, 1, 1);

            //Falsi(0, 1, 1);

            NR(1, 2);
            Console.ReadLine();
        }
    }
}
