# RabbitMQ
> AMQP(Advanced Message Queuing Protocol) 一个提供统一消息服务的应用层标准高级消息队列协议,是应用层协议的一个开放标准,为面向消息的中间件设计。

## Fanout
![Image text](https://cdn.www.sojson.com/file/doc/8914084922)

> Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息，此模式**routing_key 无作用**。

## direct
![Image text](https://cdn.www.sojson.com/file/doc/5932090818)
> 把消息路由到那些binding key与routing key完全匹配的Queue中。 

## Topic
![Image text](https://cdn.www.sojson.com/file/doc/7685815932)

> topic 是RabbitMQ中最灵活的一种方式，可以根据 routing_key 自由的绑定不同的队列

``` java
return BindingBuilder.bind(queue).to(topicExchange).with("topic.A");
```
* 讲队列 queue 绑定到 topicExchange 交换器上，routing_key：topic.A
* 当binding key与routing key相匹配时，消息将会被路由到对应的Queue中。
* routing key为一个句点号“. ”分隔的字符串（我们将被句点号“. ”分隔开的每一段独立的字符串称为一个单词），如“stock.usd.nyse”、“nyse.vmw”、“quick.orange.rabbit”
* binding key与routing key一样也是句点号“. ”分隔的字符串
* binding key中可以存在两种特殊字符“*”与“#”，用于做模糊匹配，其中“*”用于匹配一个单词，“#”用于匹配多个单词（可以是零个）

## headers

> Headers类型的Exchange不依赖于routing key与binding key的匹配规则来路由消息，而是根据发送的消息内容中的headers属性进行匹配。 在绑定Queue与Exchange时指定一组键值对；当消息发送到Exchange时，RabbitMQ会取到该消息的headers（也是一个键值对的形式），对比其中的键值对是否完全匹配Queue与Exchange绑定时指定的键值对；如果完全匹配则消息会路由到该Queue，否则不会路由到该Queue。 