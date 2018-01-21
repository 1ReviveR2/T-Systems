package com.tsystems.javaschool.tasks.calculator;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(final String statement)
    {
        if(statement == null)
            return null;
        if (statement.chars().filter(ch -> ch == '(').count() != statement.chars().filter(ch -> ch == ')').count())
            return null;
        //Будем парсить строку в классе Parser
        class Parser {
            //ind - индекс текущего элемента, cur - его ASCII код
            int ind = -1, cur;

            //перебираем элементы, удаляем пробелы
            void getChar() {
                cur = (++ind < statement.length()) ? statement.charAt(ind) : -1;
                if(Character.isWhitespace(cur)) getChar();
            }

            //проверяем, что текущий символ не является чем-то кроме цифр или закрывающей скобки
            boolean checkChar() {
                if((cur >= '0' && cur <= '9') || cur == '(')
                    return true;
                return false;
            }

            //основная функция, куда передается посчитанное значение
            Double parse() {
                getChar();
                //Передаем управление след. функции, которая в конце вернет результат в v
                Double v = parseExpression();
                if (cur != -1) {
                    return null;
                }
                //проверяем, что посчитанное значение адекватно
                if(v==Double.POSITIVE_INFINITY || v==Double.NaN || v==Double.NEGATIVE_INFINITY)
                    return null;
                return v;
            }

            //считаем разность и сумму, как самые низкоприоритетный операции
            Double parseExpression() {
                //передаем управление далее
                Double v = parseTerm();
                //бесконечный цикл, проверяющий не является ли текущий символ знаком + или -
                while(true) {
                    if (cur == '+') {
                        getChar();
                        if(checkChar()==false) return null;
                        v += parseTerm();
                    } else if (cur == '-') {
                        getChar();
                        if(checkChar()==false) return null;
                        v -= parseTerm();
                    } else {
                        return v;
                    }
                }
            }

            //делим или умножаем здесь, второй приоритет.
            Double parseTerm() {
                //передаем управление дальше
                Double v = parseFactor();
                //бесконечный цикл, проверяющий не является ли текущий символ знаком * или /
                while(true) {
                    if (cur == '/') {
                        getChar();
                        if(checkChar()==false) return null;
                        v /= parseFactor();
                    } else if (cur == '*') {
                        getChar();
                        if(checkChar()==false) return null;
                        v *= parseFactor();
                    } else {
                        return v;
                    }
                }
            }

            //проверяем не является ли символ скобкой - высший приоритет, а если нет, то имеем дело с числом и возвращаем его как объект Double
            Double parseFactor() {
                Double v;
                if (cur == '(') {
                    getChar();
                    v = parseExpression();
                    if (cur == ')') getChar();
                } else {
                    StringBuilder sb = new StringBuilder();
                    //здесь делаем что-то вроде конкатенации строк, на случай если число имеет больше одной цифры
                    while ((cur >= '0' && cur <= '9') || cur == '.') {
                        sb.append((char)cur);
                        getChar();
                    }//проверяем не пустой ли вернулся объект
                    if (sb.length() == 0) return null;
                    v = Double.parseDouble(sb.toString());
                }
                return v;
            }
        }
        //вызов нашего метода плюс исключение
        Parser p = new Parser();
        String pre_result;
        try {
            pre_result = p.parse().toString();
        }catch(Exception e){
            return null;
        }
        String result;
        if(pre_result.substring(pre_result.length() - 2).contains(".0"))
            result = pre_result.replace(".0", "");
        else
            result = pre_result;
        return result;
        }
    }
