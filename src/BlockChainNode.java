import java.util.ArrayList;

public class BlockChainNode {

    /**
     * ブロックのチェーン
     */
    private ArrayList<Block> blockChain = new ArrayList<>();

    /**
     * 検査されるブロックを保持する
     */
    private Block inspectedBlock = null;

    /**
     * BlockのData内容(ブロックのIDカウントみたいなの)
     */
    private Integer blockId = 1;

    /**
     * inspectメソッドを実行したかどうか判定(Blockを追加するとfalseに戻る)
     */
    private Boolean isRanInspect = false;

    /**
     * Constructor
     */
    public BlockChainNode() {

    }

    /**
     * @return get blockChain.
     */
    public ArrayList<Block> getBlockChain() {
        return blockChain;
    }

    /**
     * @param blockChain set blockChain.
     */
    public void setBlockChain(ArrayList<Block> blockChain) {
        this.blockChain = blockChain;
    }

    /**
     * @return get inspectedBlock.
     */
    public Block getInspectedBlock() {
        return inspectedBlock;
    }

    /**
     * @param inspectedBlock set inspectedBlock.
     */
    public void setInspectedBlock(Block inspectedBlock) {
        this.inspectedBlock = inspectedBlock;
    }

    /**
     * @return get blockId.
     */
    public Integer getBlockId() {
        return blockId;
    }

    /**
     * @param blockId set blockId.
     */
    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    /**
     * @return get isRanInspect.
     */
    public Boolean getRanInspect() {
        return isRanInspect;
    }

    /**
     * @param ranInspect set isRanInspect.
     */
    public void setRanInspect(Boolean ranInspect) {
        isRanInspect = ranInspect;
    }

    /**
     * @return get isRanInspect.
     */
    public Boolean isRanInspect() {
        return isRanInspect;
    }

    /**
     * ノードが持つチェーンにブロックを追加。ブロック受信側が利用
     */
    public void addBlock() {
        ArrayList<Block> tempBlockChain = getBlockChain();

        if (tempBlockChain.isEmpty()) { // 追加するブロックが1番目
            addBlockToChain();
        } else { // 追加するブロックが2番目以降
            addBlocksToChain();
        }

        setRanInspect(false); // ブロック追加した時inspectRunはfalseになる
    }

    /**
     * チェーンに検査済みブロックを追加する
     */
    public void addBlockToChain() {
        ArrayList<Block> tempBlockChain = getBlockChain();

        tempBlockChain.add(getInspectedBlock());
        setBlockChain(tempBlockChain);
    }

    /**
     * 検査済みブロックに前のブロックをセットし、それをブロックに追加する
     */
    public void addBlocksToChain() {
        ArrayList<Block> tempBlockChain = getBlockChain();
        Integer latestChainIndex = tempBlockChain.size() - 1;
        Block previousBlock = tempBlockChain.get(latestChainIndex); // チェーンの最後に入っているブロックを呼び出す
        Block tempInspectedBlock = getInspectedBlock();

        tempInspectedBlock.setPreviousData(previousBlock); // 追加するブロックに前のブロックを入れる
        setInspectedBlock(tempInspectedBlock);
        addBlockToChain();
    }

    /**
     * ブロックの検査メソッド(実際に検査をするわけではない.実際はブロックにデータを追加する)
     *
     * @param block ブロック
     */
    public void inspect(Block block) {
        Integer tempBlockId = getBlockId();
        Block tempInspectedBlock = getInspectedBlock();

        setInspectedBlock(block); // 検査ブロックとしてノードが保持する
        tempInspectedBlock.setData(tempBlockId); // 検査ブロックにデータを挿入する
        setBlockId(tempBlockId + 1); // カウントを増やす
        setRanInspect(true); // inspectを実行したのでTrueに
    }
}