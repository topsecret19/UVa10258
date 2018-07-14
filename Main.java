import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class contestant implements Comparable<contestant>{
	private int id;
	private int solvedCount;
	private int[] penalty;
	private boolean[] solved;
	
	public contestant(int i) {
		id = i;
		solved = new boolean[10];
		penalty= new int[10];
	}
	
	public void input(int qId, char op , int time) {
		if(op=='C'&&!solved[qId]) {
			solved[qId]=true;
			penalty[qId]+=time;
			solvedCount++;
		}else if(op=='I'&&!solved[qId]) {
			penalty[qId]+=20;
		}
	}
	
	public int getTimeTaken() {
		return Arrays.stream(penalty).sum();
	}
	
	public int compareTo(contestant c) {
		if(solvedCount!=c.solvedCount) {
			return solvedCount>c.solvedCount ? solvedCount : c.solvedCount;
		}else if(getTimeTaken() != c.getTimeTaken()) {
			return getTimeTaken() < c.getTimeTaken() ? getTimeTaken() : c.getTimeTaken();
		}else {
			return id < c.id ? id : c.id;
		}
	}
	
	public String toString() {
		return id+" "+solvedCount+" "+getTimeTaken();
	}
}
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		Scanner sc1 = new Scanner(line);
		int testCaseCount = sc1.nextInt();
		br.readLine();
		for(int i=0; i<testCaseCount; i++) {
			contestant[] clist = new contestant[101];
			String s;
			while((s=br.readLine())!=null && !s.isEmpty()) {
				Scanner sc2= new Scanner(s);
				int cId = sc2.nextInt();
				if(clist[cId]==null) {
					clist[cId] = new contestant(cId);
				}
				int qId = sc2.nextInt();
				int time = sc2.nextInt();
				char op = sc2.next().charAt(0);
				clist[cId].input(qId, op, time);
			}
			
			ArrayList<contestant> ansList = new ArrayList<contestant>();
			for(int j=1; j<clist.length; j++) {
				if(clist[j]!=null) {
					ansList.add(clist[j]);
				}
			}
			Collections.sort(ansList, Collections.reverseOrder());
			
			for(contestant c : ansList) {
				System.out.println(c.toString());
			}
			if(i>0) {
				System.out.println();
			}
		}
		
	}

}
