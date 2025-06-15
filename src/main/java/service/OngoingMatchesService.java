package service;

import game.OngoingMatch;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {


    private final Map<UUID, OngoingMatch> ongoingMatches = new ConcurrentHashMap<>();

    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();

    private OngoingMatchesService() {
    }

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }

    public void put(UUID uuid, OngoingMatch ongoingMatch) {
        ongoingMatches.put(uuid, ongoingMatch);
    }

    public OngoingMatch get(UUID uuid) {
        return ongoingMatches.get(uuid);
    }

    public void remove(UUID uuid) {
        ongoingMatches.remove(uuid);
    }

}
