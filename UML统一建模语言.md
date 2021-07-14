### UML统一建模语言

常用的关系：范化Generalization、实现Realization、关联Association、聚合Aggregation、组合Composition、依赖Dependency

- 泛化 ：就是一种继承的关系

  ![image-20201107112534733](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20201107112534733.png)

- 实现 类和接口的关系

- 关联 是一种拥有关系，它使一个类知道另一个类的属性和方法。例如 student和course，学生拥有course这个类

- 聚合 ：是整体和部分的关系，且部分可以离开整体而单独存在。例如车和轮胎的关系，聚合关系是关联关系的一种强关系

- 组合：也是整体和部分的关系，但是部分不可以离开整体/如公司和部门的关系，没有公司就没有部门。组合关系也是关联关系的一部分，比聚合关系更墙的关系

- 依赖：是一种使用关系，即一个类的使用需要另一个类的协助，所以尽量不要使用双向的相互依赖 

  **代码表现形式：** 局部变量、方法的参数or对静态方法的调用

![image-20201107113826038](C:\Users\Think\AppData\Roaming\Typora\typora-user-images\image-20201107113826038.png)