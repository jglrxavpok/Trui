TrUI
====

TrUI is an attempt at providing a graphical-library-free UI library.
For instance, it *should* be possible to switch between a LWJGL, or LibGDX backend at any time without modifying
the actual UI code.

The code inside the _core/_ folder is the code of the core library, which provides no way to render the
UI to the screen (or anything for the matter). Backends are stored in the _backends/_ folder.