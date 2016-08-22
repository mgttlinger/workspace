import model.Spirale;
import builder.Builder;
import builder.model.DefaultBuilderSpirale;

public class eclipse {
	
	public static void main(String[] args) {
		start(new DefaultBuilderSpirale());
	}

	
	public static void start(Builder<Spirale> builder) {
		Spirale s=builder.getInstance();

//		s.FileOutput();
		s.Animation();

	}
}
