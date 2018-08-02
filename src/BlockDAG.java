public class BlockDAG {

    /**
     * 全てのノードにブロックが"norma"回追加するまでシミュレートする
     */
    private Integer norma = null;

    /**
     * ノード数
     */
    private Integer nodes = null;

    /**
     * Constructor
     *
     * @param norma 上限
     * @param nodes ノード数
     */
    public BlockDAG(Integer norma, Integer nodes) {
        setNorma(norma);
        setNodes(nodes);
    }

    public void run() {
        // 実行内容を記述する
    }

    /**
     * @return get norma.
     */
    public int getNorma() {
        return norma;
    }

    /**
     * @param norma set norma.
     */
    public void setNorma(int norma) {
        this.norma = norma;
    }

    /**
     * @return get nodes.
     */
    public int getNodes() {
        return nodes;
    }

    /**
     * @param nodes set nodes.
     */
    public void setNodes(int nodes) {
        this.nodes = nodes;
    }
}