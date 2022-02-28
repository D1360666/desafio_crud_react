import React from 'react';
import StoreProvider from './store';
import FormViewC from './Components/FormView';
import List from './Components/ListView';
import FormViewT from './todo/FormView';
import ListViewT from './todo/ListView';


function App() {
  return <StoreProvider>
    <h3>To-Do List</h3>
    <FormViewC></FormViewC>
    <List></List>
    <FormViewT>
      <ListViewT></ListViewT>
    </FormViewT>
  </StoreProvider>
}

export default App;
