
public class CalculateScoreController {
	private int[] percent;
	private int[] max;
	private String[][] data;
	private double mean;
	private double sd;
	
	public CalculateScoreController(int[] max,int[] percent, String[][] data) {
		this.max = max;
		this.percent = percent;
		this.data = data;
		calculateMean(calculateSumScore());
		calculateSD(calculateSumScore());
	}
	public double[] calculateSumScore(){
		double score[] = new double[data.length];
		double sum = 0;
		
		for(int i=0; i<data.length; i++){
			for(int j=data[0].length-percent.length, k=0; j<data[0].length; j++,k++){
				sum += Double.parseDouble(data[i][j])*((double)percent[k]/(double)max[k]);
			}
			score[i] = sum;
			sum = 0;
		}
		return score;
	}
	public void calculateMean(double[] s){
		double score[] = s;
		double sum = 0;
		
		for(int i=0; i<score.length; i++){
			sum += score[i];
		}
		mean = sum / (double)score.length;
	}
	public void calculateSD(double[] s){
		double score[] = s;
		double sum = 0;
		
		for(int i=0; i<score.length; i++){
			sum += Math.pow(score[i]-mean, 2);
		}
		sd = Math.sqrt(sum/(double)data.length);
	}
	public double getMean(){
		return mean;
	}
	public double getSD(){
		return sd;
	}
}