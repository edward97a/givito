# Givito Decisions

## Stage 4

- 4.1 — store ads in `List<model.Ad>` (interface), create `ArrayList`.
- 4.2 — delete free ads with `Iterator` (no crash while deleting).
- 4.3 — check unique IDs with `Set<Integer>` on file load.
- 4.4 — find by ID with `Map<Integer, model.Ad>`, create `HashMap`.

## Problems

- New ID is calculated in 3 places: storage.FileManager, view.ConsoleView, storage.AdStorage. Fix later.
- Tasks 4.5–4.8 not done yet.