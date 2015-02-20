package languagegames.analysis;

import java.util.List;

import languagegames.AgentPairer;
import languagegames.ObjectPool;
import agent.Agent;
import conceptualspace.PerceptualObject;

public class CommunicationAnalysis implements Analysis {

	private final int numObjects;
	private final AgentPairer agentPairer;
	private final ObjectPool objectPool;

	public CommunicationAnalysis(final int numObjects, final AgentPairer agentPairer, final ObjectPool objectPool) {
		this.numObjects = numObjects;
		this.agentPairer = agentPairer;
		this.objectPool = objectPool;
	}

	@Override
	public double analyse(final List<Agent> agents) {
		final List<Integer> pairingOrder = agentPairer.pairingOrder(agents.size());
		double sumOfScores = 0;
		for (int i=0; i<pairingOrder.size(); i+=2) {
			final Agent agent = agents.get(pairingOrder.get(i));
			final Agent other = agents.get(pairingOrder.get(i+1));
			sumOfScores += communicationScore(agent, other);
		}
		return sumOfScores / (0.5 * pairingOrder.size());
	}

	private double communicationScore(final Agent agent, final Agent other) {
		final List<PerceptualObject> objects = objectPool.pick(numObjects);
		double sumOfScores = 0;
		for (final PerceptualObject object : objects) {
			sumOfScores += (agent.assertion(object).equals(other.assertion(object))) ? 1 : 0;
		}
		return sumOfScores / numObjects;
	}

}