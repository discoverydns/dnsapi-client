package com.discoverydns.dnsapiclient.internal.util;

public class Stopwatch {
	private enum stopwatchState {
		STOPPED, RUNNING
	};

	private stopwatchState state = stopwatchState.STOPPED;
	private long startTime = 0;
	private long value = 0;

	public void start() {
		if (state != stopwatchState.RUNNING) {
			state = stopwatchState.RUNNING;
			startTime = System.nanoTime();
		}
	}

	public void stop() {
		if (state != stopwatchState.STOPPED) {
			value = System.nanoTime() - startTime;
			state = stopwatchState.STOPPED;
		}
	}

	public void reset() {
		startTime = 0;
		value = 0;
		state = stopwatchState.STOPPED;
	}

	public void restart() {
		reset();
		start();
	}

	public boolean isRunning() {
		return (state == stopwatchState.RUNNING);
	}

	public long startTime() {
		return startTime;
	}

	public long elapsedTime() {
		if (state == stopwatchState.RUNNING) {
			return System.nanoTime() - startTime;
		} else {
			return value;
		}
	}

	public long elapsedTimeMs() {
		return elapsedTime() / 1000 / 1000;
	}

	public double elapsedTimeMsDecimal() {
		return elapsedTime() / 1000.0 / 1000.0;
	}
}
