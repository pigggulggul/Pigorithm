function solution (n, edge) {
  const connects = new Array(n+1).fill().map(_ => []);
  for(const e of edge) {
    connects[e[0]].push(e[1]);
    connects[e[1]].push(e[0]);
  }
    // console.log(connects);
  
  const visited = [0,1];
  const queue = [1];
  while(queue.length) {
    const cur = queue.shift();
    
    for(const next of connects[cur]) {
      if(!visited[next]) {
        visited[next] = visited[cur] + 1;
        queue.push(next);
      }
    }
  }
  
  const max = Math.max(...visited);
  
  return visited.filter(el => el === max).length;
}