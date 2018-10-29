# SpringMVC文件上传

Spring MVC为文件上传提供了直接支持,这种支持是通过即插即用的MultipartResolver实现. Spring使用Jakarta Commons FileUpload技术实现了一个MultipartResolver实现类:CommonsMultipartResolver。

在SpringMVC上下文中默认没有装配MultipartResolver,因此默认情况下不能处理文件上传工作。如果想使用Spring的文件上传功能,则需要先在上下文中配置MultipartResolver。

## 导包

```xml
<dependency>
	<groupId>commons-fileupload</groupId>
	<artifactId>commons-fileupload</artifactId>
	<version>1.3.1</version>
</dependency>

<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
<dependency>
	<groupId>commons-io</groupId>
	<artifactId>commons-io</artifactId>
	<version>2.4</version>
</dependency>
```
## 配置multipartResolver

```xml
<!--multipartResolver配置 -->
	<bean id="multipartResolver"
		class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="defaultEncoding" value="UTF-8"/>
		<property name="maxUploadSize" value="5242880"/>
		<property name="uploadTempDir" value="file:/d:/temp"/>
	</bean>
```

```xml
<form action="test/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit" value="tijiao"/>
</form>
```
```java
@RequestMapping("/upload")
public String upload(@RequestParam("file") MultipartFile file) throws IOException {
    if (file!=null){
        file.transferTo(new File("F:/"+file.getOriginalFilename()));
    }
    return "hello";
}
```
