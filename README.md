# Estrelas do Norte
Application to manage my music band Estrelas do Norte

## Models
**Music**: It represents a music (Title, Author, Genre, Path of PDF file). In the future it will also contain the lyrics and chords and it will replace the PDF file path because the PDF will be created programmatically  
  
**User Program**: It represents a user program (Style, Tempo, Transpose, Instruments (right hand), Other notes)  
  
**Live Concert Music**: It represents an instance of a Music for a Concert. It "unify" a Music with a User Program and a Singer and the transpose for the selected singer.

**Live Concert Program**: It has a name and represents a list of "live Concert Music".  
  
## Attention
- Music transpose --> In a Live Concert Program could be different

# Planned features
- Preparing a list of PDF ordered: ready for a concert
- Preparing a list of User Program ordered: ready for a concert
