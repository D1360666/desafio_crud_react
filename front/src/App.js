import React from 'react';
import StoreProvider from './store';
import List from './Components/ListView';

function App() {
  return <StoreProvider>
    <h3>To-Do List</h3>
    <List></List>
  </StoreProvider>
}

export default App;
