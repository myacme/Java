package reflex;


/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/4/20 14:31
 */

public class ReflexBean {
	@Value(value = "注解",tag = "2")
	private String string;

	private int anInt ;

	public String pub;

    public ReflexBean bean;

	 public ReflexBean(ReflexBean bean){
	 	this.bean = bean;
	 }
	 public ReflexBean(String s){
	 	this.string = s;
	 }

	 public ReflexBean(int i){
	 	this.anInt = i;
	 }

	 private ReflexBean(String s,int i){
	 	this.anInt = i;
	 	this.string = s;
	 }

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	private int getAnInt() {
		return anInt;
	}

	public void setAnInt(int anInt) {
		this.anInt = anInt;
	}

    public ReflexBean getBean() {
        return bean;
    }

    public void setBean(ReflexBean bean) {
        this.bean = bean;
    }

    @Override
	public String toString() {
		return "ReflexBean{" +
				"string='" + string + '\'' +
				", anInt=" + anInt +
				", pub='" + pub + '\'' +
				'}';
	}
}