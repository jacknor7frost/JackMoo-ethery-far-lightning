# Ethery Far Lightning

<img width="1901" height="923" alt="Screenshot 2026-11-15 072913" src="https://github.com/user-attachments/assets/3c18a37f-7ff5-4e8c-bc1c-56df12f2d90e" />


Extends lightning bolt visibility beyond vanilla's limit. Works within your render distance. Note: This mod works within your render/tracking distance. It does not render bolts in Distant Horizons or Voxy LOD terrain.

Performance friendly: //Mixin (render cull override) Renders 1–2 extra bolt entities Bolts last ~2 seconds, then despawn. 
No continuous overhead. 
//Thunder sound (packet) 24 bytes every 2–6 seconds One tiny packet + one audio playback. //Config check One if statement per bolt render Zero measurable cost. What it does NOT do: *No per-tick calculations *No particle systems *No continuous rendering *No global entity broadcast changes *No additional entities spawned 
*No chunk loading The worst case: a thunderstorm with bolts hitting every 2 seconds. 
Your GPU renders one extra 1×128×1 box for 2 seconds, then it's gone. 
That's less than 0.01% of your frame budget.

## Requirements
- Minecraft 1.20.1
- Forge 47.4.20+

## License
MIT   
