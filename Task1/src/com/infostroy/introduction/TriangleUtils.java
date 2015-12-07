package com.infostroy.introduction;

public class TriangleUtils {

	/**
	 * Задача о треугольнике
	 * 
	 * Вам даны длинны трех отрезков: a, b, c. Нужно вернуть true, если они
	 * могут быть сторонами треугольника и false, если не могут.
	 *
	 */

	public boolean isTriangle(int a, int b, int c) throws IllegalArgumentException {
		if (a <= 0 || b <= 0 || c <= 0) {
			throw new IllegalArgumentException();
		}
		if ((a + b <= c) || (a + c <= b) || (b + c <= a)) {
			return false;
		}
		return true;
	}

	/**
	 * Вам даны длинны трех сторон треугольника: a, b, c. Необходимо вычислить
	 * площадь треугольника.
	 */

	public double getTriangleArea(int a, int b, int c) throws IllegalArgumentException {
		if (!isTriangle(a, b, c)) {
			return 0;
		}
		double semiperimeter = (a + b + c) / 2.;
		return Math.sqrt(semiperimeter * (semiperimeter - a) * (semiperimeter - b) * (semiperimeter - c));
	}
}
