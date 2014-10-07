package core.applicationService.mapServices.connectivity.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.applicationService.informerServices.Observer;
import core.applicationService.mapServices.connectivity.IUnion;
import core.applicationService.mapServices.connectivity.IWeightedUnionPathCompression;
import core.contract.ITDLogger;
import core.domain.waves.Position;

/**
 * @author mojtaba
 * in this case the order of growth is lg*n for the n nodes in union functionality.
 * lg* 65536 = lg* 2^16 = lg 2+lg 2^4 = 1+ lg2 + Lg 4= 1+1+lg2+lg2 =4 !
 */
@Component
public class WeightedUnionPathCompression implements IUnion, IWeightedUnionPathCompression {

	private int[] unionArray;
	private int[] unionSize;
	private Map<Position, Integer> unionMap;
	private ITDLogger logger;
	
	@Autowired
    public void setLogger(ITDLogger logger) {
		this.logger = logger;
	}
	/* (non-Javadoc)
	 * @see core.applicationService.mapServices.connectivity.imp.IWeightedUnionPathCompression#initialize(int, int)
	 */
	@Override
	public void initialize(int n, int m){
		try {
			unionArray = new int[n * m];
			unionSize = new int[n * m];
			unionMap = new HashMap<>();
			int index = 0;
			for(int i = 0;i < n; i++){
				for(int j = 0; j < m; j++){
					unionArray[index] = index;
					unionSize[index] = 1;
					Position position = new Position(i, j);
					unionMap.put(position, index);
					index++;
				}
			}
		} catch (Exception e) {
			logger.writer("WeightedUnionPathCompression--> initialize", e);
		}
	}
	/* (non-Javadoc)
	 * @see core.applicationService.mapServices.connectivity.imp.IWeightedUnionPathCompression#connected(core.domain.waves.Position, core.domain.waves.Position)
	 */
	@Override
	public boolean connected(Position p, Position q){
		int pKey = (Integer)unionMap.get(p);
		int qkey = unionMap.get(q);
		return root(pKey) == root(qkey);
	}
	/* (non-Javadoc)
	 * @see core.applicationService.mapServices.connectivity.imp.IWeightedUnionPathCompression#root(int)
	 */
	@Override
	public int root(int pKey){
		try {
			while (pKey != unionArray[pKey]) {
				unionArray[pKey] = unionArray[unionArray[pKey]];
				pKey = unionArray[pKey];
			}
		} catch (Exception e) {
			logger.writer("WeightedUnionPathCompression--> root", e);
		}
		return pKey;
	}
	/* (non-Javadoc)
	 * @see core.applicationService.mapServices.connectivity.imp.IWeightedUnionPathCompression#union(core.domain.waves.Position, core.domain.waves.Position)
	 */
	@Override
	public void union(Position p, Position q){
		try {
			int pKey = (Integer)unionMap.get(p);
			int qkey = unionMap.get(q);
			int i = root(pKey);
			int j = root(qkey);
			if( i == j) return;
			if(unionSize[i] < unionSize[j]){
				unionArray[i] = j;
				unionSize[j] += unionSize[i];
			}else{
				unionArray[j] = i;
				unionSize[i] += unionSize[j];
			}
		} catch (Exception e) {
			logger.writer("WeightedUnionPathCompression--> union", e);
		}

	}
}