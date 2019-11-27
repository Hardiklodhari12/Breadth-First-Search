/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;
import java.util.*;
/**
 *
 * @author iis
 */
public class bfs {
    
    public static class Qcont{
        String grid;
        int lvl;
        Qcont(String str,int num){
            grid = str;
            lvl = num;
        }
    }
    
    public static String conv(int inp[][]){
        String ans = "";
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                ans = ans + inp[i][j];
            }
        }
        
        return ans;
    }
    
    public static int[][] getst(String str){
    
        int arr[][] = new int[3][3];
        char carr[] = str.toCharArray();
        int k=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                arr[i][j] = (carr[k] - '0');
                k++;
            }
        }
        
        return arr;
    }
    
    static boolean valid(int x,int y){
        return (x>=0 && x<3 && y>=0 && y<3);
    }
    
    public static int searchgbfs(int inp[][],int goal[][],int cutd){

        HashSet<String> vis  = new HashSet<String>();
        int ans = -1;
        Queue<Qcont> qc = new LinkedList<Qcont>();
        String goalst = conv(goal);
        String inpst = conv(inp);
        Qcont qob = new Qcont(inpst,0);
        qc.offer(qob);
        
        while(!qc.isEmpty()){
        
            Qcont curq = qc.peek();
            qc.poll();
     
            String curst = curq.grid;
            vis.add(curst);
            int level = curq.lvl;
                        
            if(curst.equals(goalst)){
                return level;
            }
            
            int st[][] = getst(curst);
            if(level!=cutd){
                  int dx[] = {1,-1,0,0};
                  int dy[] = {0,0,1,-1};
                  int blx = -1;
                  int bly=-1;
                  for(int i=0;i<3;i++){
                      for(int j=0;j<3;j++){
                          if(st[i][j]==0){
                              blx = i;
                              bly = j;
                          }
                      }
                  }
                  
                  for(int k=0;k<4;k++){
                      if(valid(blx + dx[k],bly+dy[k])){
                          st[blx][bly] = st[blx + dx[k]][bly+dy[k]];
                          st[blx + dx[k]][bly + dy[k]] = 0;
                          String newst = conv(st);
                          int newl = level + 1;
                          if(!vis.contains(newst)){
                            Qcont newq = new Qcont(newst,newl);
                            qc.offer(newq);
                          }
                          st[blx + dx[k]][bly + dy[k]] = st[blx][bly];
                          st[blx][bly] = 0;
                      }
                  }
                  
            }
         
            
        }
        
        return -1;
    }
    

    public static void main(String[] args) {
        
        System.out.println("Enter the input grid. Enter 0 for blank cell");
        Scanner sc = new Scanner(System.in);
        int inp[][] = new int[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                inp[i][j] = sc.nextInt();
            }
        }
        
        System.out.println("Enter the cut off depth for search");
        int cutd = sc.nextInt();
        
        System.out.println("Enter the goal state. Enter 0 for blank cell");
        int goal[][] = new int[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                goal[i][j] = sc.nextInt();
            }
        }
        
        int ans = bfs.searchgbfs(inp,goal,cutd);
        
        if(ans!=-1){
            System.out.println("Goal is found after " + ans + " steps");
        }

        else{
            System.out.println("Goal cannot be reached");
        }
        
    }
    
}
