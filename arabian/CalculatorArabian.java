package arabian;

import java.util.Scanner;

public class CalculatorArabian {
    public static void main(String[] args) {
        //2+3
        //V+V=X
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите выражение");
        String exp = scn.nextLine();
        //Определяем арифметическое действие
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;

            }else{
                System.out.println("В римской системе нет отрицательных чисел");
            }
        }
        //Если не нашли арифметического действия, то завершаем программу
        if (actionIndex ==-1) {


            System.out.println("Некорректный ввод данных");
            return;
        }
        //"1+5".split("\\+")->{"1", "5"}
        String[] data = exp.split(regexActions[actionIndex]);
        //Определяем, находятся ли числа в одном формате (римские, либо арабские)
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman){
                //Если римские, то конвертируем в арабские
                //X+V
                //X-10
                //V - 5
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            }else{
                //Если арабские, то конвертируем из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            //Выполняем арифметическое действие с числами

            int result = switch (actions[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };

            //15->xv
            //Если числа были арабские, то возвращаем результат в арабском числе
            if(isRoman) {
                System.out.println(converter.intToRoman(result));
            } else{
                //Если числа были римские, то возвращаем результат в римском числе
                System.out.println(result);
            }
        } else {
            System.out.println("Числа должны быть в одном формате");
        }
    }
}