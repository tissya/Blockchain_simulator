/**
Proof of Stakeを行うプログラム
*/

import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;
import org.apache.commons.math3.distribution.ParetoDistribution;

class PoS{
    private int nodeNum = 0;
    private int trial = 0;
    private int sumValue = 0; //すべてのノードが持つ通貨の合計

    public PoS(int nodeNum, int trial) {
        setNodeNum(nodeNum);
        setTrial(trial);
    }

    public void run(){
        Node[] node = new Node[nodeNum];
        ArrayList<Integer> lottery = new ArrayList<Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        int excavator = 0;
        for(int i = 0 ; i < nodeNum ; i++){
            node[i].setCurrency(pareto(i));
            sumValue += node[i].getCurrency();
            lottery.add(node[i].getCurrency());
        }

        /**
         * PoSシステムの試行
         */
        for(int j = 0 ; j < trial ; j++){
        excavator = proof(lottery);
        node[excavator].makeBlock();
        }

        /**
         * 試行終了後に各ノードが生成したブロック数をリストに突っ込む
         */
        for(int i = 0; i < nodeNum ; i++) result.add(node[i].getMakeBlocks());

        return;
    }

    public int proof(ArrayList list){
        //以下、ルーレット選択によるPoSのマイニング成功者選択
        Random random = new Random();
        int hit = random.nextInt(sumValue);

        for(int j = 0; j < list.size(); j++){
            hit -= (int)list.get(j);
            if(hit < 0) return j;
        }
        System.out.println("これが表示されたらおかしいゾ");
        return -1;
    }

    /**
     * べき分布に従って通貨量を決定
     */
    public int pareto(int number){
        ParetoDistribution d = new ParetoDistribution();
        int ans = (int)(d.density(number) * 2000);
        return ans;
    }

    /**
     * 以下、Getter,Setter
     * @return
     */
    public int getNodeNum() {
        return this.nodeNum;
    }

    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }

    public int getTrial() {
        return this.trial;
    }

    public void setTrial(int trial) {
        this.trial = trial;
    }

    public int getSumValue() {
        return this.sumValue;
    }

    public void setSumValue(int sumValue) {
        this.sumValue = sumValue;
    }

}