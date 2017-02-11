#include <iostream>
#include <cstring>
#include <cstdio>
#define C 1000001
using namespace std;

int point,line,st,de,js,head,nail,po,a,b,c;
bool judge[C];
int dui[C],linehead[C],depoint[C],cost[C],next[C],dis[C];

int join(int a1,int b1,int c1)
{
	po++;
	depoint[po]=b1;
	cost[po]=c1;
	next[po]=linehead[a1];
	linehead[a1]=po;
}

int spfa()
{
	int ch;
	memset(judge,0,sizeof(judge));
	for (int i=1;i<=1000001;++i)
	  dis[i]=999999999;	
	//for (int i=1;i<=1000001;++i)
	  //cout<<dis[i];
	head=1; nail=0; dui[1]=st; 
	dis[st]=0; judge[st]=true;
	while (nail<=head)
	  {
	     ch=dui[++nail];
	     po=linehead[ch];
	     judge[ch]=false;
	     while (po>0) 
	       {
	          if (cost[po]+dis[ch]<dis[depoint[po]])
	            {
	            	dis[depoint[po]]=cost[po]+dis[ch];
	            	if (judge[depoint[po]]==false)
	            	  {
	            	  	dui[++head]=depoint[po];
	            	  	judge[depoint[po]]=true;
					  }
				}
			  po=next[po];
		   }
	  }
}

int main()
  {
  	po=0;
  	cin>>point>>line>>st>>de;
  	for (int js=1;js<=line;++js)
    {
      scanf("%d%d%d",&a,&b,&c);
      join(a,b,c);
      join(b,a,c);   
	}
	spfa();
	//for (int i=1;i<=10;++i)
	  //cout<<dis[i];
	cout<<dis[de]<<endl;
  	
  }
