# Overlay bootstrap

This directory contains tooling for the upcoming "layer 4" workflow. The goal is to
prepare a clean workspace that pulls specific commits (SHA) of our forks for each layer
(Trixnity, Trixnity-Messenger, TeleCrypt) and keeps them in sync automatically.

## Files
- `config.example.json` – sample configuration. Copy to `overlay/config.json` and adjust
  repository URLs / refs to match the commits you want to pin.
- `bootstrap.sh` – clones/fetches every layer, checks out the requested ref, and exposes
  the result under `overlay/workspace/<targetDir>`.

## Usage
```bash
cp overlay/config.example.json overlay/config.json   # edit refs/URLs as needed
./overlay/bootstrap.sh                               # creates overlay/workspace/...
```

Each entry in the config looks like this:
```json
{
  "name": "trixnity",
  "repo": "https://github.com/TeleCrypt-io/trixnity.git",
  "ref": "feature/calls",
  "targetDir": "layers/trixnity"
}
```
- `name` – logical label used for cache directories.
- `repo` – git URL (HTTPS or SSH) of our fork.
- `ref` – branch/commit/tag to check out. You can point to any SHA even if upstream
  hasn't merged the change yet.
- `targetDir` – relative path under `overlay/workspace` where the files will be placed.

The script keeps a bare clone cached under `.overlay/cache/<name>` so subsequent runs are
fast and do not re-download the entire repo. If you change the ref, re-run `bootstrap.sh`
and the workspace gets updated.

> Note: editing layer 1/2 should still happen in their native repositories. The overlay is
> responsible for **assembling** the desired versions of each layer so we can build and run
> TeleCrypt even if upstream hasn't taken our patches.
