class ConcretePrototype implements Cloneable{
	public Prototype clone(){
		Object object = null;
		try{
			object = super.clone();
		}catch(CloneNotSupportedException e){
			System.err.println("Not Support cloneable")
		}
		return (Prototype)object;
	}
}


//深度克隆
// 原型、克隆对象都需要实现接口Serializable.

// 克隆方法使用序列化技术实现。
public WeeklyLog deepClone()throw IOException, ClassNotFundException, OptionalDataException{
	//将对象写入流中。
	ByteArrayOutputStream bao = new ByteArrayOutputStream();
	ObjectOutputStream oos = new ObjectOutputStream(bao);
	oos.writeObject(this);

	//将对象从流中取出

	ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
	ObjectInputStream ois = new ObjectInputStream(bis);
	return (WeeklyLog)ois.readObject();
}