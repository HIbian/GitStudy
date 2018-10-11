# 反射

## 三种方式获取类Class
反射的入口Class
* Class.forName("");
* 对象.getClass();
* 类名.class;

## 获取类的属性
* 获取类的属性 Class.getDeclaredFields():Field[]
* 属性名 Field.getName():String
* 修饰符 Field.getModifiers():int
  * 0---默认
  * 1---public
  * 2---private
  * 4---protected
  * 8---static
* 属性类型 Field.getType():String
*

## 创建对象
* Class.newInstance(); 调用无参构造创建对象

## 获取类所用方法
* Class.getDeclaredMethods():Method[]
* Method.getReturnType():Class
* Method.getModifiers():int
* Method.getName:String
* 参数
  * Method.getParameterTypes():Class[] .getName()获取参数类型名
  * Method.getParameters:Parameter[]   .getName()获取参数名
